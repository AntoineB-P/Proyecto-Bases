import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-intro-balon',
  standalone: false,
  templateUrl: './intro-balon.html',
  styleUrl: './intro-balon.css'
})
export class IntroBalon implements OnInit, OnDestroy {

  listo = false;       // true cuando el balón 3D ya cargó
  terminado = false;   // true cuando la intro ya se puede quitar del HTML

  private temporizadores: number[] = [];

  ngOnInit(): void {
    // quien pidió menos movimiento no ve la intro
    if (window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
      this.terminado = true;
      return;
    }

    // mientras dura la intro, la página de atrás queda en pausa (ver styles.css)
    document.body.classList.add('intro-activa');

    // plan B: si el balón tarda más de 4 segundos en cargar, nos saltamos la intro
    this.temporizadores.push(window.setTimeout(() => {
      if (!this.listo) { this.saltar(); }
    }, 4000));
  }

  ngOnDestroy(): void {
    this.temporizadores.forEach(t => clearTimeout(t));
    document.body.classList.remove('intro-activa');
  }

  // el balón terminó de cargar: empieza la patada
  alCargar(): void {
    this.listo = true;

    // a los 1.5 s la página de atrás despierta
    this.temporizadores.push(window.setTimeout(() => {
      document.body.classList.remove('intro-activa');
    }, 1500));

    // a los 2.8 s todo se desvaneció y quitamos la intro del HTML
    this.temporizadores.push(window.setTimeout(() => {
      this.terminado = true;
    }, 2800));
  }


  saltar(): void {
    document.body.classList.remove('intro-activa');
    this.terminado = true;
  }
}
