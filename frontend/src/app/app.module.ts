import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';


// https://www.npmjs.com/package/ng2-cookies

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { AltausuariosComponent } from './components/altausuarios/altausuarios.component';
import { ListausuariosComponent } from './components/listausuarios/listausuarios.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AltausuariosComponent,
    ListausuariosComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
