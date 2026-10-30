import {
  CUSTOM_ELEMENTS_SCHEMA,
  NgModule,
  provideBrowserGlobalErrorListeners,
} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Dashboard } from './paginas/dashboard/dashboard';
import { Estadio3d } from './componentes/estadio3dcomponent/estadio3d';
import { BarraLateral } from './layout/barralateral/barralateral';
import { BarraSuperior } from './layout/barrasuperior/barrasuperior';
import { IntroBalon } from './componentes/introbaloncomponent/introbalon';
import { BalonHero } from './componentes/balonherocomponent/balonhero';

  'balonherocomponent/balonherocomponent';

@NgModule({
  declarations: [App, BarraLateral, BarraSuperior, Dashboard, Estadio3d, IntroBalon, BalonHero],
  imports: [BrowserModule, AppRoutingModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [provideBrowserGlobalErrorListeners()],
  bootstrap: [App],
})
export class AppModule {}
