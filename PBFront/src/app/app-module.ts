import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { BarraLateral } from './layout/barra-lateral/barra-lateral';
import { BarraSuperior } from './layout/barra-superior/barra-superior';
import { Dashboard } from './paginas/dashboard/dashboard';
import { Estadio3d } from './componentes/estadio3d/estadio3d';

@NgModule({
  declarations: [App, BarraLateral, BarraSuperior, Dashboard, Estadio3d],
  imports: [BrowserModule, AppRoutingModule],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {}
