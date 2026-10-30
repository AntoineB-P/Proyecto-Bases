import {
  CUSTOM_ELEMENTS_SCHEMA,
  NgModule,
  provideBrowserGlobalErrorListeners,
} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { BarraLateral } from './layout/barra-lateral/barra-lateral';
import { BarraSuperior } from './layout/barra-superior/barra-superior';
import { Dashboard } from './paginas/dashboard/dashboard';
import { Estadio3d } from './componentes/estadio3dcomponent/estadio3d';
import { IntroBalon } from './componentes/introbaloncomponent/intro-balon';
import { BalonHero } from './componentes/balonherocomponent/balon-hero';

  'balonherocomponent/balonherocomponent';

@NgModule({
  declarations: [App, BarraLateral, BarraSuperior, Dashboard, Estadio3d, IntroBalon, BalonHero],
  imports: [BrowserModule, AppRoutingModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {}
