import { Component, OnInit } from '@angular/core';

@Component({
  standalone: false,
  selector: 'app-introbalon',
  templateUrl: './introbalon.html',
  styleUrls: ['./introbalon.css']
})
export class IntroBalon implements OnInit {

  listo = false;
  terminado = false;

  ngOnInit() {
    if (window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
      this.terminado = true;
      return;
    }
    document.body.classList.add('intro-activa');

    setTimeout(() => this.terminar(), 4500);
  }

  alCargar() {
    this.listo = true;
    setTimeout(() => document.body.classList.remove('intro-activa'), 1500);
    setTimeout(() => this.terminar(), 2800);
  }

  terminar() {
    document.body.classList.remove('intro-activa');
    this.terminado = true;
  }
}
