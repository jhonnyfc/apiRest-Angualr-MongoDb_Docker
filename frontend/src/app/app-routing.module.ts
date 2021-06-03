import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AltausuariosComponent } from './components/altausuarios/altausuarios.component';
import { LoginComponent } from './components/login/login.component';

// ng g c components/alta --skipTests
// ng generate service services/mododata
// ng g guard guards/authv --skipTests

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo:'login'
  },
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'altausuarios',
    component: AltausuariosComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
