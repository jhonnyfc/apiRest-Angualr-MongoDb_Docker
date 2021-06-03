import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AltausuariosComponent } from './components/altausuarios/altausuarios.component';
import { ListausuariosComponent } from './components/listausuarios/listausuarios.component';
import { LoginComponent } from './components/login/login.component';
import { AuthvGuard } from './guards/authv.guard';

// ng g c components/listausuarios --skipTests
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
  {
    path:'listausers',
    component: ListausuariosComponent,
    canActivate: [AuthvGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
