import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { ConsultaService } from '../../services/consulta.service';
import { EquipoService } from '../../services/equipo.service';
import { PosicionModel } from '../../models/posicion.model';
import { EquipoModel } from '../../models/equipo.model';
import { HistorialEquipoModel } from '../../models/historial-equipo.model';

@Component({
  standalone: false,
  selector: 'posiciones',
  templateUrl: './posiciones.component.html',
  styleUrls: ['./posiciones.component.css']
})
export class PosicionesComponent implements OnInit {

  tabla: PosicionModel[] = [];
  equipos: EquipoModel[] = [];
  historial: HistorialEquipoModel[] = [];
  elegido: PosicionModel | null = null;
  idTorneo = 1;
  clasificados = 8;
  cargando = true;
  cargandoHistorial = false;
  mensajeError = '';

  constructor(
    private consultaService: ConsultaService,
    private equipoService: EquipoService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.consultaService.getTablaPosiciones(this.idTorneo).subscribe({
      next: (lista) => {
        this.tabla = lista;
        this.cargando = false;
        if (lista.length > 0) {
          this.verHistorial(lista[0]);
        }
        this.cdr.detectChanges();
      },
      error: () => {
        this.mensajeError = 'No se pudo cargar la tabla. Revisa que el back esté prendido.';
        this.cargando = false;
        this.cdr.detectChanges();
      }
    });
    this.equipoService.getListar().subscribe({
      next: (lista) => {
        this.equipos = lista;
        this.cdr.detectChanges();
      },
      error: () => {}
    });
  }

  escudoDe(idEquipo: number): string {
    for (let e of this.equipos) {
      if (e.idEquipo === idEquipo) {
        return e.escudoUrl || '';
      }
    }
    return '';
  }

  verHistorial(fila: PosicionModel): void {
    this.elegido = fila;
    this.historial = [];
    this.cargandoHistorial = true;
    this.consultaService.getHistorialEquipo(fila.idEquipo, this.idTorneo).subscribe({
      next: (lista) => {
        this.historial = lista;
        this.cargandoHistorial = false;
        this.cdr.detectChanges();
      },
      error: () => {
        this.cargandoHistorial = false;
        this.cdr.detectChanges();
      }
    });
  }

  textoResultado(letra: string): string {
    if (letra === 'G') {
      return 'Ganó';
    }
    if (letra === 'E') {
      return 'Empató';
    }
    return 'Perdió';
  }
}
