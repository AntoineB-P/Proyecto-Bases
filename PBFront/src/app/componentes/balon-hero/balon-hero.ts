import { AfterViewInit, Component, ElementRef, NgZone, OnDestroy, ViewChild } from '@angular/core';

@Component({
  selector: 'app-balon-hero',
  standalone: false,
  templateUrl: './balon-hero.html',
  styleUrl: './balon-hero.css'
})
export class BalonHero implements AfterViewInit, OnDestroy {

  @ViewChild('capa', { static: true }) capa!: ElementRef<HTMLElement>;
  @ViewChild('balon', { static: true }) balon!: ElementRef<HTMLElement & { cameraOrbit: string }>;

  private idAnimacion = 0;
  private raton = { x: 0, y: 0, px: 0, py: 0 };
  private suave = { x: 0, y: 0 };
  private giro = 0;
  private reducirMovimiento = false;

  constructor(private zona: NgZone) {}

  ngAfterViewInit(): void {
    this.reducirMovimiento = window.matchMedia('(prefers-reduced-motion: reduce)').matches;
    window.addEventListener('mousemove', this.alMoverRaton);

    this.zona.runOutsideAngular(() => this.animar());
  }

  ngOnDestroy(): void {
    cancelAnimationFrame(this.idAnimacion);
    window.removeEventListener('mousemove', this.alMoverRaton);
  }

  private alMoverRaton = (e: MouseEvent): void => {
    this.raton.x = e.clientX / window.innerWidth - 0.5;
    this.raton.y = e.clientY / window.innerHeight - 0.5;
    this.raton.px = e.clientX;
    this.raton.py = e.clientY;
  };

  private animar = (): void => {
    this.idAnimacion = requestAnimationFrame(this.animar);

    // si el héroe ya salió de la pantalla, no gastamos recursos
    if (window.scrollY > window.innerHeight * 1.2) { return; }

    // suavizado: el balón no salta hacia el mouse, lo va persiguiendo
    this.suave.x += (this.raton.x - this.suave.x) * 0.05;
    this.suave.y += (this.raton.y - this.suave.y) * 0.05;

    // qué tan cerca está el mouse del balón (0 = lejos, 1 = encima)
    const caja = this.balon.nativeElement.getBoundingClientRect();
    const dx = this.raton.px - (caja.left + caja.width / 2);
    const dy = this.raton.py - (caja.top + caja.height / 2);
    const cerca = Math.max(0, 1 - Math.hypot(dx, dy) / 450);

    // el balón gira solo, y gira más rápido si el mouse se le acerca
    if (!this.reducirMovimiento) { this.giro += 0.4 + cerca * 3; }

    // la cámara del modelo mira hacia donde está el mouse
    this.balon.nativeElement.cameraOrbit =
      `${this.giro + this.suave.x * 90}deg ${75 + this.suave.y * 40}deg auto`;

    // parallax: la capa se mueve al revés del mouse y más lento que el scroll
    this.capa.nativeElement.style.transform =
      `translate3d(${-this.suave.x * 40}px, ${-this.suave.y * 30 + window.scrollY * 0.3}px, 0)`;
  };
}
