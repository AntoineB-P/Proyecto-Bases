import { AfterViewInit, Component, ElementRef, HostListener, NgZone, OnDestroy, ViewChild } from '@angular/core';

@Component({
  standalone: false,
  selector: 'app-balonhero',
  templateUrl: './balonhero.html',
  styleUrls: ['./balonhero.css']
})
export class BalonHero implements AfterViewInit, OnDestroy {

  @ViewChild('capa') capa!: ElementRef;
  @ViewChild('modelo') modelo!: ElementRef;

  mouse = { x: 0, y: 0, px: 0, py: 0 };
  suave = { x: 0, y: 0 };
  giro = 0;
  idAnimacion = 0;
  reducir = window.matchMedia('(prefers-reduced-motion: reduce)').matches;

  constructor(private zona: NgZone) {}

  ngAfterViewInit() {
    this.zona.runOutsideAngular(() => this.animar());
  }

  ngOnDestroy() {
    cancelAnimationFrame(this.idAnimacion);
  }

  @HostListener('window:mousemove', ['$event'])
  moverMouse(e: MouseEvent) {
    this.mouse.x = e.clientX / window.innerWidth - 0.5;
    this.mouse.y = e.clientY / window.innerHeight - 0.5;
    this.mouse.px = e.clientX;
    this.mouse.py = e.clientY;
  }

  animar() {
    this.idAnimacion = requestAnimationFrame(() => this.animar());

    if (window.scrollY > window.innerHeight * 1.2) { return; }

    this.suave.x += (this.mouse.x - this.suave.x) * 0.05;
    this.suave.y += (this.mouse.y - this.suave.y) * 0.05;

    const caja = this.modelo.nativeElement.getBoundingClientRect();
    const dx = this.mouse.px - (caja.left + caja.width / 2);
    const dy = this.mouse.py - (caja.top + caja.height / 2);
    const cerca = Math.max(0, 1 - Math.sqrt(dx * dx + dy * dy) / 450);

    if (!this.reducir) { this.giro += 0.4 + cerca * 3; }

    this.modelo.nativeElement.cameraOrbit = `${this.giro + this.suave.x * 90}deg ${75 + this.suave.y * 40}deg auto`;

    this.capa.nativeElement.style.transform =
      `translate3d(${-this.suave.x * 40}px, ${-this.suave.y * 30 + window.scrollY * 0.3}px, 0)`;
  }
}
