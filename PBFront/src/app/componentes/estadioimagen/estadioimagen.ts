import { Component, ElementRef, HostListener, ViewChild } from '@angular/core';

@Component({
  standalone: false,
  selector: 'app-estadioimagen',
  templateUrl: './estadioimagen.html',
  styleUrls: ['./estadioimagen.css'],
})
export class Estadioimagen {
  @ViewChild('contenedor') contenedor!: ElementRef;
  @ViewChild('foto') foto!: ElementRef;

  @HostListener('window:mousemove', ['$event'])
  moverMouse(e: MouseEvent) {
    const x = e.clientX / window.innerWidth - 0.5;
    const y = e.clientY / window.innerHeight - 0.5;
    this.foto.nativeElement.style.transform = `translate3d(${-x * 30}px, ${-y * 20}px, 0) scale(1.08)`;
  }

  bajar() {
    const fondo = this.contenedor.nativeElement.getBoundingClientRect().bottom + window.scrollY;
    window.scrollTo({ top: fondo, behavior: 'smooth' });
  }
}
