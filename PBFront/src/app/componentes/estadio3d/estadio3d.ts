import { AfterViewInit, Component, ElementRef, HostListener, NgZone, OnDestroy, ViewChild } from '@angular/core';
import * as THREE from 'three';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';

@Component({
  selector: 'app-estadio3d',
  standalone: false,
  templateUrl: './estadio3d.html',
  styleUrl: './estadio3d.css'
})
export class Estadio3d implements AfterViewInit, OnDestroy {

  @ViewChild('contenedor') contenedor!: ElementRef;
  @ViewChild('lienzo') lienzo!: ElementRef;
  @ViewChild('capaTitulo') capaTitulo!: ElementRef;
  @ViewChild('capaPista') capaPista!: ElementRef;

  // piezas de three.js
  renderer!: THREE.WebGLRenderer;
  escena = new THREE.Scene();
  camara = new THREE.PerspectiveCamera(42, 1, 0.1, 500);
  controles!: OrbitControls;
  mundo = new THREE.Group();    // todo el estadio vive dentro de este grupo
  gradas = new THREE.Group();   // las gradas son círculos aplastados: así se vuelven un óvalo
  balon!: THREE.Mesh;
  jugadores: { malla: THREE.Mesh, x: number, z: number }[] = [];
  destellos: THREE.PointsMaterial[] = [];

  // mouse y animación
  mouse = { x: 0, y: 0 };
  suave = { x: 0, y: 0 };
  idAnimacion = 0;
  reducir = window.matchMedia('(prefers-reduced-motion: reduce)').matches;

  constructor(private zona: NgZone) {}

  ngAfterViewInit() {
    // runOutsideAngular: evita que Angular revise toda la página 60 veces por segundo
    this.zona.runOutsideAngular(() => {
      this.crearEscena();
      this.animar();
    });
  }

  ngOnDestroy() {
    cancelAnimationFrame(this.idAnimacion);
    this.renderer.dispose();
  }

  @HostListener('window:mousemove', ['$event'])
  moverMouse(e: MouseEvent) {
    this.mouse.x = (e.clientX / window.innerWidth) * 2 - 1;
    this.mouse.y = (e.clientY / window.innerHeight) * 2 - 1;
  }

  @HostListener('window:resize')
  cambiarTamano() {
    const caja = this.contenedor.nativeElement;
    this.camara.aspect = caja.clientWidth / caja.clientHeight;
    this.camara.updateProjectionMatrix();
    this.renderer.setSize(caja.clientWidth, caja.clientHeight, false);
  }

  bajar() {
    const fondo = this.contenedor.nativeElement.getBoundingClientRect().bottom + window.scrollY;
    window.scrollTo({ top: fondo, behavior: 'smooth' });
  }

  crearEscena() {
    this.renderer = new THREE.WebGLRenderer({ canvas: this.lienzo.nativeElement, antialias: true, alpha: true });
    this.renderer.setPixelRatio(Math.min(window.devicePixelRatio, 2));
    this.cambiarTamano();

    this.escena.fog = new THREE.Fog(0x0a0a09, 80, 230);
    this.camara.position.set(0, 36, 92);
    this.escena.add(this.mundo);
    this.mundo.add(this.gradas);
    this.gradas.scale.set(1, 1, 0.68);

    // luces
    this.escena.add(new THREE.HemisphereLight(0xf2ead8, 0x0a0a09, 1));
    const sol = new THREE.DirectionalLight(0xffe2b0, 1.2);
    sol.position.set(-30, 60, 40);
    this.escena.add(sol);

    // piezas del estadio
    const brillo = this.dibujarBrillo();
    this.crearAura(brillo);
    this.crearCancha();
    this.crearNivel(26, 33, 6, 0, 28);    // grada baja
    this.crearNivel(34, 42, 8, 7, 34);    // grada alta
    this.crearAdornos();
    this.crearTorres(brillo);
    this.crearDestellos(brillo);
    this.crearJugadores();
    this.crearBalon();

    // arrastrar con el mouse para girar el estadio
    this.controles = new OrbitControls(this.camara, this.lienzo.nativeElement);
    this.controles.target.set(0, 4, 0);
    this.controles.enableDamping = true;
    this.controles.enablePan = false;
    this.controles.enableZoom = false;
    this.controles.maxPolarAngle = Math.PI / 2 - 0.08;
    this.controles.autoRotate = !this.reducir;
    this.controles.autoRotateSpeed = 0.7;
    this.lienzo.nativeElement.style.touchAction = 'pan-y';
  }

  dibujarBrillo() {
    const canvas = document.createElement('canvas');
    canvas.width = 128;
    canvas.height = 128;
    const g = canvas.getContext('2d')!;
    const degradado = g.createRadialGradient(64, 64, 0, 64, 64, 64);
    degradado.addColorStop(0, 'rgba(255,255,255,1)');
    degradado.addColorStop(0.25, 'rgba(255,255,255,0.45)');
    degradado.addColorStop(1, 'rgba(255,255,255,0)');
    g.fillStyle = degradado;
    g.fillRect(0, 0, 128, 128);
    return new THREE.CanvasTexture(canvas);
  }

  dibujarCancha() {
    const canvas = document.createElement('canvas');
    canvas.width = 1050;
    canvas.height = 680;
    const g = canvas.getContext('2d')!;

    for (let i = 0; i < 14; i++) {
      g.fillStyle = i % 2 === 0 ? '#0f3b28' : '#144a33';
      g.fillRect(i * 75, 0, 75, 680);
    }

    g.strokeStyle = 'rgba(242, 234, 216, 0.92)';
    g.fillStyle = 'rgba(242, 234, 216, 0.92)';
    g.lineWidth = 4;
    g.strokeRect(6, 6, 1038, 668);                                      // borde
    g.beginPath(); g.moveTo(525, 6); g.lineTo(525, 674); g.stroke();    // línea de mitad
    g.beginPath(); g.arc(525, 340, 91.5, 0, Math.PI * 2); g.stroke();   // círculo central
    g.beginPath(); g.arc(525, 340, 5, 0, Math.PI * 2); g.fill();
    g.strokeRect(6, 138.5, 165, 403);                                   // áreas grandes
    g.strokeRect(879, 138.5, 165, 403);
    g.strokeRect(6, 248.4, 55, 183.2);                                  // áreas chicas
    g.strokeRect(989, 248.4, 55, 183.2);
    g.beginPath(); g.arc(116, 340, 5, 0, Math.PI * 2); g.fill();        // penales
    g.beginPath(); g.arc(934, 340, 5, 0, Math.PI * 2); g.fill();
    g.beginPath(); g.arc(116, 340, 91.5, -0.927, 0.927); g.stroke();    // medialunas
    g.beginPath(); g.arc(934, 340, 91.5, Math.PI - 0.927, Math.PI + 0.927); g.stroke();

    const textura = new THREE.CanvasTexture(canvas);
    textura.colorSpace = THREE.SRGBColorSpace;
    return textura;
  }

  dibujarGradas(repetir: number) {
    const canvas = document.createElement('canvas');
    canvas.width = 256;
    canvas.height = 128;
    const g = canvas.getContext('2d')!;
    g.fillStyle = '#1a1917';
    g.fillRect(0, 0, 256, 128);

    const colores = ['#f2ead8', '#d4af72', '#b8975a', '#f2c94c', '#b33a33', '#6b6458', '#3a3833'];
    for (let fila = 0; fila < 8; fila++) {
      g.fillStyle = 'rgba(184, 151, 90, 0.22)';
      g.fillRect(0, fila * 16, 256, 2);
      for (let x = 0; x < 256; x += 10) {
        if (Math.random() < 0.18) { continue; }    // silla vacía
        g.fillStyle = colores[Math.floor(Math.random() * colores.length)];
        g.fillRect(x + 1, fila * 16 + 5, 7, 9);
      }
    }

    const textura = new THREE.CanvasTexture(canvas);
    textura.colorSpace = THREE.SRGBColorSpace;
    textura.wrapS = THREE.RepeatWrapping;
    textura.repeat.set(repetir, 1);
    return textura;
  }

  dibujarFachada() {
    const canvas = document.createElement('canvas');
    canvas.width = 128;
    canvas.height = 64;
    const g = canvas.getContext('2d')!;
    g.fillStyle = '#000000';
    g.fillRect(0, 0, 128, 64);
    g.fillStyle = '#ffffff';
    for (let x = 4; x < 128; x += 16) { g.fillRect(x, 0, 3, 64); }    // columnas de luz
    const textura = new THREE.CanvasTexture(canvas);
    textura.wrapS = THREE.RepeatWrapping;
    textura.repeat.set(10, 1);
    return textura;
  }

  crearAura(brillo: THREE.CanvasTexture) {
    const geo = new THREE.PlaneGeometry(190, 190).rotateX(-Math.PI / 2);
    const mat = new THREE.MeshBasicMaterial({
      map: brillo, color: 0xb8975a, transparent: true, opacity: 0.4,
      blending: THREE.AdditiveBlending, depthWrite: false
    });
    const aura = new THREE.Mesh(geo, mat);
    aura.position.y = -0.2;
    this.mundo.add(aura);
  }

  crearCancha() {
    const geo = new THREE.PlaneGeometry(42, 27.2).rotateX(-Math.PI / 2);
    const mat = new THREE.MeshStandardMaterial({ map: this.dibujarCancha(), roughness: 0.95 });
    this.mundo.add(new THREE.Mesh(geo, mat));
  }

  crearNivel(radioAbajo: number, radioArriba: number, alto: number, y: number, repetir: number) {
    const textura = this.dibujarGradas(repetir);
    const geo = new THREE.CylinderGeometry(radioArriba, radioAbajo, alto, 96, 1, true);
    const mat = new THREE.MeshStandardMaterial({
      map: textura, emissive: 0xffffff, emissiveMap: textura, emissiveIntensity: 0.55,
      roughness: 1, side: THREE.DoubleSide
    });
    const nivel = new THREE.Mesh(geo, mat);
    nivel.position.y = y + alto / 2;
    this.gradas.add(nivel);
  }

  crearAdornos() {
    const dorado = new THREE.MeshBasicMaterial({ color: 0xd4af72, side: THREE.DoubleSide });

    const franja = new THREE.Mesh(new THREE.CylinderGeometry(34, 33, 1, 96, 1, true), dorado);
    franja.position.y = 6.5;
    this.gradas.add(franja);

    const borde = new THREE.Mesh(new THREE.TorusGeometry(42, 0.3, 8, 96).rotateX(Math.PI / 2), dorado);
    borde.position.y = 15;
    this.gradas.add(borde);

    const geo = new THREE.CylinderGeometry(43, 46, 15.6, 96, 1, true);
    const mat = new THREE.MeshStandardMaterial({
      color: 0x15140f, emissive: 0xb8975a, emissiveMap: this.dibujarFachada(),
      emissiveIntensity: 0.9, roughness: 0.8
    });
    const fachada = new THREE.Mesh(geo, mat);
    fachada.position.y = 7.8;
    this.gradas.add(fachada);
  }

  crearTorres(brillo: THREE.CanvasTexture) {
    const esquinas = [[-46, -33], [46, -33], [-46, 33], [46, 33]];

    for (const esquina of esquinas) {
      const x = esquina[0];
      const z = esquina[1];

      const poste = new THREE.Mesh(
        new THREE.CylinderGeometry(0.5, 0.9, 30, 8),
        new THREE.MeshStandardMaterial({ color: 0x2a2824 })
      );
      poste.position.set(x, 15, z);
      this.mundo.add(poste);

      const destino = new THREE.Vector3(-x * 0.25, 0, -z * 0.25);
      const lampara = new THREE.Group();
      lampara.position.set(x, 31, z);
      lampara.lookAt(destino);
      this.mundo.add(lampara);

      const panel = new THREE.Mesh(
        new THREE.BoxGeometry(8, 3.4, 0.8),
        new THREE.MeshBasicMaterial({ color: 0xfff1cf })
      );
      lampara.add(panel);

      const resplandor = new THREE.Sprite(new THREE.SpriteMaterial({
        map: brillo, color: 0xffe2b0, transparent: true,
        blending: THREE.AdditiveBlending, depthWrite: false
      }));
      resplandor.scale.set(30, 30, 1);
      lampara.add(resplandor);

      const largo = lampara.position.distanceTo(destino);
      const haz = new THREE.Mesh(
        new THREE.ConeGeometry(10, largo, 32, 1, true).rotateX(-Math.PI / 2),
        new THREE.MeshBasicMaterial({
          color: 0xf2ead8, transparent: true, opacity: 0.055,
          blending: THREE.AdditiveBlending, depthWrite: false, side: THREE.DoubleSide
        })
      );
      haz.position.z = largo / 2;
      lampara.add(haz);
    }
  }

  crearDestellos(brillo: THREE.CanvasTexture) {
    for (let grupo = 0; grupo < 2; grupo++) {
      const posiciones: number[] = [];

      for (let i = 0; i < 450; i++) {
        const esAlta = Math.random() < 0.4;
        const radioAbajo = esAlta ? 34 : 26;
        const radioArriba = esAlta ? 42 : 33;
        const alto = esAlta ? 8 : 6;
        const y = esAlta ? 7 : 0;
        const f = Math.random();
        const radio = radioAbajo + (radioArriba - radioAbajo) * f - 0.4;
        const angulo = Math.random() * Math.PI * 2;
        posiciones.push(Math.cos(angulo) * radio, y + f * alto + 0.25, Math.sin(angulo) * radio);
      }

      const geo = new THREE.BufferGeometry();
      geo.setAttribute('position', new THREE.Float32BufferAttribute(posiciones, 3));
      const mat = new THREE.PointsMaterial({
        map: brillo, color: 0xfff2c8, size: 1.1, transparent: true,
        blending: THREE.AdditiveBlending, depthWrite: false
      });
      this.gradas.add(new THREE.Points(geo, mat));
      this.destellos.push(mat);
    }
  }

  crearJugadores() {
    const formacion = [
      [-18.5, 0],
      [-15, -9], [-15, -3], [-15, 3], [-15, 9],
      [-10, -9], [-10, -3], [-10, 3], [-10, 9],
      [-5.5, -3.5], [-5.5, 3.5]
    ];
    const geo = new THREE.CylinderGeometry(0.4, 0.4, 1.6, 10);
    const dorado = new THREE.MeshStandardMaterial({ color: 0xd4af72, emissive: 0xd4af72, emissiveIntensity: 0.45 });
    const crema = new THREE.MeshStandardMaterial({ color: 0xf2ead8, emissive: 0xf2ead8, emissiveIntensity: 0.45 });

    for (const p of formacion) {
      this.ponerJugador(geo, dorado, p[0], p[1]);
      this.ponerJugador(geo, crema, -p[0], p[1]);
    }
  }

  ponerJugador(geo: THREE.BufferGeometry, mat: THREE.Material, x: number, z: number) {
    const malla = new THREE.Mesh(geo, mat);
    malla.position.set(x, 0.8, z);
    this.mundo.add(malla);
    this.jugadores.push({ malla: malla, x: x, z: z });
  }

  crearBalon() {
    const geo = new THREE.SphereGeometry(0.75, 20, 20);
    const mat = new THREE.MeshStandardMaterial({ color: 0xffffff, emissive: 0xffffff, emissiveIntensity: 0.5 });
    this.balon = new THREE.Mesh(geo, mat);
    this.mundo.add(this.balon);
  }

  animar() {
    this.idAnimacion = requestAnimationFrame(() => this.animar());

    const t = performance.now() / 1000;
    const alto = this.contenedor.nativeElement.offsetHeight;
    const scroll = window.scrollY;

    this.suave.x += (this.mouse.x - this.suave.x) * 0.06;
    this.suave.y += (this.mouse.y - this.suave.y) * 0.06;
    this.capaTitulo.nativeElement.style.transform =
      `translate3d(${-this.suave.x * 16}px, ${-this.suave.y * 10 + scroll * 0.25}px, 0)`;
    this.capaPista.nativeElement.style.transform =
      `translate3d(${-this.suave.x * 30}px, ${-this.suave.y * 18 + scroll * 0.4}px, 0)`;

    if (scroll > alto * 1.05) { return; }

    const progreso = Math.min(scroll / alto, 1);
    this.mundo.rotation.x = progreso * 0.45;
    this.mundo.position.y = -progreso * 6;

    if (!this.reducir) {
      this.balon.position.set(Math.sin(t * 0.6) * 14, 0.75 + Math.abs(Math.sin(t * 3)) * 1.4, Math.sin(t * 0.9 + 1) * 8);

      this.jugadores.forEach((j, i) => {
        j.malla.position.x = j.x + Math.sin(t * 0.8 + i) * 1.6;
        j.malla.position.z = j.z + Math.cos(t * 0.7 + i) * 1.2;
      });

      this.destellos[0].opacity = 0.15 + Math.abs(Math.sin(t * 2.3)) * 0.85;
      this.destellos[1].opacity = 0.15 + Math.abs(Math.cos(t * 1.7)) * 0.85;
    }

    this.controles.update();
    this.renderer.render(this.escena, this.camara);
  }
}
