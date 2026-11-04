import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Dashboard } from './paginas/dashboard/dashboard';
import { Login } from './login/login';
import { Registro } from './registro/registro';
import { PosicionesComponent } from './paginas/posiciones/posiciones.component';
import { EquiposComponent } from './paginas/equipos/equipos.component';
import { JugadoresComponent } from './paginas/jugadores/jugadores.component';
import { PartidosComponent } from './paginas/partidos/partidos.component';
import { ArbitrosComponent } from './paginas/arbitros/arbitros.component';
import { EstadiosComponent } from './paginas/estadios/estadios.component';
import { TorneosComponent } from './paginas/torneos/torneos.component';

const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: Dashboard },
  { path: 'posiciones', component: PosicionesComponent },
  { path: 'equipos', component: EquiposComponent },
  { path: 'jugadores', component: JugadoresComponent },
  { path: 'partidos', component: PartidosComponent },
  { path: 'arbitros', component: ArbitrosComponent },
  { path: 'estadios', component: EstadiosComponent },
  { path: 'torneos', component: TorneosComponent },
  { path: 'login', component: Login },
  { path: 'registro', component: Registro },
  { path: '**', redirectTo: 'dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
