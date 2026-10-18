import { AfterViewInit, Component, ElementRef, NgZone, OnDestroy, ViewChild } from '@angular/core';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';

@Component({
  selector: 'app-estadio3d',
  standalone: false,
  templateUrl: './estadio3d.html',
  styleUrl: './estadio3d.css'
})
export class Estadio3d implements AfterViewInit, OnDestroy {

  @ViewChild('contenedor', { static: true }) contenedor!: ElementRef<HTMLElement>;
  @ViewChild('lienzo', { static: true }) lienzo!: ElementRef<HTMLCanvasElement>;
  @ViewChild('capaTitulo', { static: true }) capaTitulo!: ElementRef<HTMLElement>;
  @ViewChild('capaPista', { static: true }) capaPista!: ElementRef<HTMLElement>;

  sinWebgl = false;

  // ---- piezas de three.js ----
  private renderer!: THREE.WebGLRenderer;
  private escena!: THREE.Scene;
  private camara!: THREE.PerspectiveCamera;
  private controles!: OrbitControls;
  private mundo!: THREE.Group;
  private gradas!: THREE.Group;
  private balon!: THREE.Mesh;
  private jugadores: { malla: THREE.Mesh; baseX: number; baseZ: number; fase: number; vel: number }[] = [];
  private matDestellosA!: THREE.PointsMaterial;
  private matDestellosB!: THREE.PointsMaterial;

  // todo lo que hay que liberar al salir de la página
  private desechables: { dispose(): void }[] = [];

  private idAnimacion = 0;
  private temporizador = 0;
  private reducirMovimiento = false;
  private puntero = { x: 0, y: 0, suaveX: 0, suaveY: 0 };

  constructor(private zona: NgZone) {}

  ngAfterViewInit(): void {
    // runOutsideAngular: el estadio se redibuja 60 veces por segundo y
    // Angular no necesita revisar cambios en cada cuadro
    this.zona.runOutsideAngular(() => {
      try {
        this.crearEscena();
      } catch (error) {
        console.error('No se pudo crear el estadio 3D', error);
        this.zona.run(() => { this.sinWebgl = true; });
        return;
      }
      this.animar();
    });
  }

  ngOnDestroy(): void {
    cancelAnimationFrame(this.idAnimacion);
    clearTimeout(this.temporizador);
    window.removeEventListener('resize', this.alRedimensionar);
    this.contenedor.nativeElement.removeEventListener('pointermove', this.alMoverPuntero);
    if (this.controles) { this.controles.dispose(); }
    this.desechables.forEach(d => d.dispose());
    if (this.renderer) { this.renderer.dispose(); }
  }

  bajar(): void {
    const fondo = this.contenedor.nativeElement.getBoundingClientRect().bottom + window.scrollY;
    window.scrollTo({ top: fondo, behavior: 'smooth' });
  }

  // =====================================================================
  //  ESCENA
  // =====================================================================
  private crearEscena(): void {
    const cont = this.contenedor.nativeElement;
    const ancho = cont.clientWidth;
    const alto = cont.clientHeight;
    this.reducirMovimiento = window.matchMedia('(prefers-reduced-motion: reduce)').matches;

    this.renderer = new THREE.WebGLRenderer({ canvas: this.lienzo.nativeElement, antialias: true, alpha: true });
    this.renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));
    this.renderer.setSize(ancho, alto, false);
    this.renderer.setClearColor(0x000000, 0);

    this.escena = new THREE.Scene();
    this.escena.fog = new THREE.Fog(0x0a0a09, 80, 230);

    this.camara = new THREE.PerspectiveCamera(ancho < alto ? 60 : 42, ancho / alto, 0.1, 500);
    this.camara.position.set(0, 36, 92);

    this.mundo = new THREE.Group();
    this.escena.add(this.mundo);

    // luces
    this.escena.add(new THREE.HemisphereLight(0xf2ead8, 0x0a0a09, 1.0));
    const luzPrincipal = new THREE.DirectionalLight(0xffe2b0, 1.2);
    luzPrincipal.position.set(-30, 60, 40);
    this.escena.add(luzPrincipal);

    const brillo = this.texturaBrillo();
    this.desechables.push(brillo);

    this.crearAura(brillo);
    this.crearCancha();
    this.crearGradas();
    this.crearTorres(brillo);
    this.crearDestellos(brillo);
    this.crearJugadores();
    this.crearBalon();

    // controles: arrastrar para girar el estadio
    this.controles = new OrbitControls(this.camara, this.lienzo.nativeElement);
    this.controles.target.set(0, 4, 0);
    this.controles.enableDamping = true;
    this.controles.dampingFactor = 0.06;
    this.controles.enablePan = false;
    this.controles.enableZoom = false;          // así la rueda del mouse sigue haciendo scroll en la página
    this.controles.minPolarAngle = 0.35;
    this.controles.maxPolarAngle = Math.PI / 2 - 0.08;
    this.controles.autoRotate = !this.reducirMovimiento;
    this.controles.autoRotateSpeed = 0.7;
    this.lienzo.nativeElement.style.touchAction = 'pan-y';   // en celular, deslizar hacia abajo sigue haciendo scroll

    this.controles.addEventListener('start', () => {
      this.controles.autoRotate = false;
      clearTimeout(this.temporizador);
    });
    this.controles.addEventListener('end', () => {
      this.temporizador = window.setTimeout(() => {
        if (!this.reducirMovimiento) { this.controles.autoRotate = true; }
      }, 2500);
    });

    window.addEventListener('resize', this.alRedimensionar);
    cont.addEventListener('pointermove', this.alMoverPuntero);
  }

  // ---------- texturas dibujadas con canvas (como dibujar en un papel) ----------
  private texturaBrillo(): THREE.CanvasTexture {
    const c = document.createElement('canvas');
    c.width = 128;
    c.height = 128;
    const g = c.getContext('2d')!;
    const grad = g.createRadialGradient(64, 64, 0, 64, 64, 64);
    grad.addColorStop(0, 'rgba(255,255,255,1)');
    grad.addColorStop(0.25, 'rgba(255,255,255,0.45)');
    grad.addColorStop(1, 'rgba(255,255,255,0)');
    g.fillStyle = grad;
    g.fillRect(0, 0, 128, 128);
    return new THREE.CanvasTexture(c);
  }

  private texturaCancha(): THREE.CanvasTexture {
    // 10 píxeles por metro: la cancha mide 105 x 68 metros
    const c = document.createElement('canvas');
    c.width = 1050;
    c.height = 680;
    const g = c.getContext('2d')!;

    const franjas = 14;
    const ancho = c.width / franjas;
    for (let i = 0; i < franjas; i++) {
      g.fillStyle = i % 2 === 0 ? '#0f3b28' : '#144a33';
      g.fillRect(i * ancho, 0, ancho, c.height);
    }

    g.strokeStyle = 'rgba(242, 234, 216, 0.92)';
    g.fillStyle = 'rgba(242, 234, 216, 0.92)';
    g.lineWidth = 4;

    g.strokeRect(6, 6, 1038, 668);                       // borde
    g.beginPath(); g.moveTo(525, 6); g.lineTo(525, 674); g.stroke();   // línea de mitad
    g.beginPath(); g.arc(525, 340, 91.5, 0, Math.PI * 2); g.stroke();  // círculo central
    g.beginPath(); g.arc(525, 340, 5, 0, Math.PI * 2); g.fill();

    g.strokeRect(6, 138.5, 165, 403);                    // áreas grandes
    g.strokeRect(879, 138.5, 165, 403);
    g.strokeRect(6, 248.4, 55, 183.2);                   // áreas chicas
    g.strokeRect(989, 248.4, 55, 183.2);

    g.beginPath(); g.arc(116, 340, 5, 0, Math.PI * 2); g.fill();       // penales
    g.beginPath(); g.arc(934, 340, 5, 0, Math.PI * 2); g.fill();
    g.beginPath(); g.arc(116, 340, 91.5, -0.927, 0.927); g.stroke();   // medialunas
    g.beginPath(); g.arc(934, 340, 91.5, Math.PI - 0.927, Math.PI + 0.927); g.stroke();

    const tex = new THREE.CanvasTexture(c);
    tex.colorSpace = THREE.SRGBColorSpace;
    tex.anisotropy = this.renderer.capabilities.getMaxAnisotropy();
    return tex;
  }

  private texturaGradas(repiteX: number): THREE.CanvasTexture {
    const c = document.createElement('canvas');
    c.width = 256;
    c.height = 128;
    const g = c.getContext('2d')!;
    g.fillStyle = '#1a1917';
    g.fillRect(0, 0, 256, 128);

    const colores = ['#f2ead8', '#d4af72', '#b8975a', '#f2c94c', '#b33a33', '#6b6458', '#3a3833'];
    for (let fila = 0; fila < 8; fila++) {
      const y = fila * 16;
      g.fillStyle = 'rgba(184, 151, 90, 0.22)';
      g.fillRect(0, y, 256, 2);                            // borde de cada escalón
      for (let x = 0; x < 256; x += 10) {
        if (Math.random() < 0.18) { continue; }            // sillas vacías
        g.fillStyle = colores[Math.floor(Math.random() * colores.length)];
        g.fillRect(x + 1, y + 5, 7, 9);                    // una persona
      }
    }

    const tex = new THREE.CanvasTexture(c);
    tex.colorSpace = THREE.SRGBColorSpace;
    tex.wrapS = THREE.RepeatWrapping;
    tex.wrapT = THREE.RepeatWrapping;
    tex.repeat.set(repiteX, 1);
    return tex;
  }

  private texturaFachada(): THREE.CanvasTexture {
    const c = document.createElement('canvas');
    c.width = 128;
    c.height = 64;
    const g = c.getContext('2d')!;
    g.fillStyle = '#000000';
    g.fillRect(0, 0, 128, 64);
    g.fillStyle = '#ffffff';
    for (let x = 4; x < 128; x += 16) { g.fillRect(x, 0, 3, 64); }   // columnas de luz
    const tex = new THREE.CanvasTexture(c);
    tex.wrapS = THREE.RepeatWrapping;
    tex.repeat.set(10, 1);
    return tex;
  }

  // ---------- piezas del estadio ----------
  private crearAura(brillo: THREE.CanvasTexture): void {
    const geo = new THREE.PlaneGeometry(190, 190);
    geo.rotateX(-Math.PI / 2);
    const mat = new THREE.MeshBasicMaterial({
      map: brillo, color: 0xb8975a, transparent: true, opacity: 0.4,
      blending: THREE.AdditiveBlending, depthWrite: false
    });
    const aura = new THREE.Mesh(geo, mat);
    aura.position.y = -0.2;
    this.mundo.add(aura);
    this.desechables.push(geo, mat);
  }

  private crearCancha(): void {
    const tex = this.texturaCancha();
    const geo = new THREE.PlaneGeometry(42, 27.2);
    geo.rotateX(-Math.PI / 2);
    const mat = new THREE.MeshStandardMaterial({ map: tex, roughness: 0.95 });
    this.mundo.add(new THREE.Mesh(geo, mat));
    this.desechables.push(tex, geo, mat);
  }

  private crearGradas(): void {
    // las gradas son círculos aplastados en Z: un círculo estirado se vuelve óvalo
    this.gradas = new THREE.Group();
    this.gradas.scale.set(1, 1, 0.68);
    this.mundo.add(this.gradas);

    const dorado = new THREE.MeshBasicMaterial({ color: 0xd4af72 });
    const doradoDoble = new THREE.MeshBasicMaterial({ color: 0xd4af72, side: THREE.DoubleSide });
    this.desechables.push(dorado, doradoDoble);

    // nivel bajo y nivel alto
    const niveles = [
      { rAbajo: 26, rArriba: 33, alto: 6, y: 0, repite: 28 },
      { rAbajo: 34, rArriba: 42, alto: 8, y: 7, repite: 34 }
    ];
    for (const n of niveles) {
      const tex = this.texturaGradas(n.repite);
      const geo = new THREE.CylinderGeometry(n.rArriba, n.rAbajo, n.alto, 96, 1, true);
      const mat = new THREE.MeshStandardMaterial({
        map: tex, emissive: 0xffffff, emissiveMap: tex, emissiveIntensity: 0.55,
        roughness: 1, side: THREE.DoubleSide
      });
      const malla = new THREE.Mesh(geo, mat);
      malla.position.y = n.y + n.alto / 2;
      this.gradas.add(malla);
      this.desechables.push(tex, geo, mat);
    }

    // franja dorada entre los dos niveles
    const geoFranja = new THREE.CylinderGeometry(34, 33, 1, 96, 1, true);
    const franja = new THREE.Mesh(geoFranja, doradoDoble);
    franja.position.y = 6.5;
    this.gradas.add(franja);
    this.desechables.push(geoFranja);

    // borde superior dorado
    const geoBorde = new THREE.TorusGeometry(42, 0.3, 8, 96);
    geoBorde.rotateX(Math.PI / 2);
    const borde = new THREE.Mesh(geoBorde, dorado);
    borde.position.y = 15;
    this.gradas.add(borde);
    this.desechables.push(geoBorde);

    // fachada exterior con columnas de luz dorada
    const texFachada = this.texturaFachada();
    const geoFachada = new THREE.CylinderGeometry(43, 46, 15.6, 96, 1, true);
    const matFachada = new THREE.MeshStandardMaterial({
      color: 0x15140f, emissive: 0xb8975a, emissiveMap: texFachada, emissiveIntensity: 0.9, roughness: 0.8
    });
    const fachada = new THREE.Mesh(geoFachada, matFachada);
    fachada.position.y = 7.8;
    this.gradas.add(fachada);
    this.desechables.push(texFachada, geoFachada, matFachada);
  }

  private crearTorres(brillo: THREE.CanvasTexture): void {
    const geoPoste = new THREE.CylinderGeometry(0.5, 0.9, 30, 8);
    const matPoste = new THREE.MeshStandardMaterial({ color: 0x2a2824, roughness: 0.7 });
    const geoLampara = new THREE.BoxGeometry(8, 3.4, 0.8);
    const matLampara = new THREE.MeshBasicMaterial({ color: 0xfff1cf });
    const matBrillo = new THREE.SpriteMaterial({
      map: brillo, color: 0xffe2b0, transparent: true,
      blending: THREE.AdditiveBlending, depthWrite: false
    });
    const matHaz = new THREE.MeshBasicMaterial({
      color: 0xf2ead8, transparent: true, opacity: 0.055,
      blending: THREE.AdditiveBlending, depthWrite: false, side: THREE.DoubleSide
    });
    this.desechables.push(geoPoste, matPoste, geoLampara, matLampara, matBrillo, matHaz);

    const esquinas = [[-46, -33], [46, -33], [-46, 33], [46, 33]];
    for (const [x, z] of esquinas) {
      const poste = new THREE.Mesh(geoPoste, matPoste);
      poste.position.set(x, 15, z);
      this.mundo.add(poste);

      const lampara = new THREE.Mesh(geoLampara, matLampara);
      lampara.position.set(x, 31, z);
      lampara.lookAt(0, 0, 0);
      this.mundo.add(lampara);

      const resplandor = new THREE.Sprite(matBrillo);
      resplandor.position.set(x, 31, z);
      resplandor.scale.set(30, 30, 1);
      this.mundo.add(resplandor);

      // haz de luz: un cono que va desde la lámpara hasta la cancha
      const origen = new THREE.Vector3(x, 31, z);
      const destino = new THREE.Vector3(-x * 0.25, 0, -z * 0.25);
      const largo = origen.distanceTo(destino);
      const geoHaz = new THREE.ConeGeometry(10, largo, 32, 1, true);
      geoHaz.translate(0, -largo / 2, 0);   // la punta queda en el origen
      geoHaz.rotateX(-Math.PI / 2);         // y la base apunta hacia +Z
      const haz = new THREE.Mesh(geoHaz, matHaz);
      haz.position.copy(origen);
      haz.lookAt(destino);
      this.mundo.add(haz);
      this.desechables.push(geoHaz);
    }
  }

  private crearDestellos(brillo: THREE.CanvasTexture): void {
    // puntitos de luz repartidos por las gradas, como los flashes del público
    const crearGrupo = (cantidad: number): THREE.PointsMaterial => {
      const posiciones = new Float32Array(cantidad * 3);
      for (let i = 0; i < cantidad; i++) {
        const alto = Math.random() < 0.4;
        const rAbajo = alto ? 34 : 26;
        const rArriba = alto ? 42 : 33;
        const yBase = alto ? 7 : 0;
        const altura = alto ? 8 : 6;
        const f = Math.random();
        const r = rAbajo + (rArriba - rAbajo) * f - 0.4;
        const ang = Math.random() * Math.PI * 2;
        posiciones[i * 3] = Math.cos(ang) * r;
        posiciones[i * 3 + 1] = yBase + f * altura + 0.25;
        posiciones[i * 3 + 2] = Math.sin(ang) * r;
      }
      const geo = new THREE.BufferGeometry();
      geo.setAttribute('position', new THREE.BufferAttribute(posiciones, 3));
      const material = new THREE.PointsMaterial({
        map: brillo, color: 0xfff2c8, size: 1.1, transparent: true, opacity: 0.8,
        blending: THREE.AdditiveBlending, depthWrite: false
      });
      this.gradas.add(new THREE.Points(geo, material));
      this.desechables.push(geo, material);
      return material;
    };

    this.matDestellosA = crearGrupo(450);
    this.matDestellosB = crearGrupo(450);
  }

  private crearJugadores(): void {
    const geo = new THREE.CylinderGeometry(0.4, 0.4, 1.6, 10);
    const matLocal = new THREE.MeshStandardMaterial({ color: 0xd4af72, emissive: 0xd4af72, emissiveIntensity: 0.45 });
    const matVisita = new THREE.MeshStandardMaterial({ color: 0xf2ead8, emissive: 0xf2ead8, emissiveIntensity: 0.45 });
    this.desechables.push(geo, matLocal, matVisita);

    // formación 4-4-2: [qué tan adelante, qué tan a lo ancho]
    const formacion = [
      [0.02, 0.5],
      [0.18, 0.12], [0.18, 0.37], [0.18, 0.63], [0.18, 0.88],
      [0.4, 0.12], [0.4, 0.37], [0.4, 0.63], [0.4, 0.88],
      [0.62, 0.35], [0.62, 0.65]
    ];

    for (const lado of [-1, 1]) {
      for (const [profundo, ancho] of formacion) {
        const malla = new THREE.Mesh(geo, lado === -1 ? matLocal : matVisita);
        const baseX = lado * (19 - profundo * 22);
        const baseZ = (ancho - 0.5) * 24;
        malla.position.set(baseX, 0.8, baseZ);
        this.mundo.add(malla);
        this.jugadores.push({
          malla, baseX, baseZ,
          fase: Math.random() * Math.PI * 2,
          vel: 0.5 + Math.random() * 0.6
        });
      }
    }
  }

  private crearBalon(): void {
    const geo = new THREE.SphereGeometry(0.75, 20, 20);
    const mat = new THREE.MeshStandardMaterial({ color: 0xffffff, emissive: 0xffffff, emissiveIntensity: 0.5 });
    this.balon = new THREE.Mesh(geo, mat);
    this.balon.position.set(0, 0.75, 0);
    this.mundo.add(this.balon);
    this.desechables.push(geo, mat);
  }

  // =====================================================================
  //  EVENTOS
  // =====================================================================
  private alMoverPuntero = (e: PointerEvent): void => {
    const r = this.contenedor.nativeElement.getBoundingClientRect();
    this.puntero.x = ((e.clientX - r.left) / r.width) * 2 - 1;
    this.puntero.y = ((e.clientY - r.top) / r.height) * 2 - 1;
  };

  private alRedimensionar = (): void => {
    const c = this.contenedor.nativeElement;
    const ancho = c.clientWidth;
    const alto = c.clientHeight;
    this.camara.aspect = ancho / alto;
    this.camara.fov = ancho < alto ? 60 : 42;
    this.camara.updateProjectionMatrix();
    this.renderer.setSize(ancho, alto, false);
  };

  // =====================================================================
  //  BUCLE DE ANIMACIÓN (se ejecuta en cada cuadro)
  // =====================================================================
  private animar = (): void => {
    this.idAnimacion = requestAnimationFrame(this.animar);

    const t = performance.now() / 1000;
    const alturaHero = this.contenedor.nativeElement.offsetHeight;
    const scroll = window.scrollY;

    // parallax de los textos: cada capa se mueve distinto con el mouse y con el scroll
    this.puntero.suaveX += (this.puntero.x - this.puntero.suaveX) * 0.06;
    this.puntero.suaveY += (this.puntero.y - this.puntero.suaveY) * 0.06;
    this.capaTitulo.nativeElement.style.transform =
      `translate3d(${-this.puntero.suaveX * 16}px, ${-this.puntero.suaveY * 10 + scroll * 0.25}px, 0)`;
    this.capaPista.nativeElement.style.transform =
      `translate3d(${-this.puntero.suaveX * 30}px, ${-this.puntero.suaveY * 18 + scroll * 0.4}px, 0)`;

    // si el estadio ya salió de la pantalla, no gastamos GPU dibujándolo
    if (scroll > alturaHero * 1.05) { return; }

    // parallax del estadio con el scroll: se inclina y se aleja
    const progreso = Math.min(scroll / alturaHero, 1);
    this.mundo.rotation.x = progreso * 0.45;
    this.mundo.position.y = -progreso * 6;

    if (!this.reducirMovimiento) {
      // el balón recorre la cancha y rebota
      this.balon.position.x = Math.sin(t * 0.6) * 14;
      this.balon.position.z = Math.sin(t * 0.9 + 1) * 8;
      this.balon.position.y = 0.75 + Math.abs(Math.sin(t * 3)) * 1.4;

      // los jugadores se mueven un poco alrededor de su posición
      for (const j of this.jugadores) {
        j.malla.position.x = j.baseX + Math.sin(t * j.vel + j.fase) * 1.6;
        j.malla.position.z = j.baseZ + Math.cos(t * j.vel * 0.8 + j.fase) * 1.2;
      }

      // los flashes del público parpadean en contratiempo
      this.matDestellosA.opacity = 0.15 + Math.abs(Math.sin(t * 2.3)) * 0.85;
      this.matDestellosB.opacity = 0.15 + Math.abs(Math.cos(t * 1.7)) * 0.85;
    }

    this.controles.update();
    this.renderer.render(this.escena, this.camara);
  };
}
