import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { BarraLateral } from './layout/barralateral/barralateral';
import { BarraSuperior } from './layout/barrasuperior/barrasuperior';
import { Dashboard } from './paginas/dashboard/dashboard';
import { IntroBalon } from './componentes/introbaloncomponent/introbalon';
import { BalonHero } from './componentes/balonherocomponent/balonhero';
import { AuthInterceptor } from './services/auth.interceptor';
import { Login } from './login/login';
import { Registro } from './registro/registro';
import { Estadioimagen } from './componentes/estadioimagen/estadioimagen';

@NgModule({
  declarations: [
    App,
    BarraLateral,
    BarraSuperior,
    Dashboard,
    Login,
    Registro,
    IntroBalon,
    BalonHero,
    Estadioimagen,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [App],
})
export class AppModule {}
