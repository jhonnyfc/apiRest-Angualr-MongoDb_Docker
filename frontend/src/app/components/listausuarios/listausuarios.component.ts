import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { Usuario } from 'src/app/utils/interfaces';

@Component({
  selector: 'app-listausuarios',
  templateUrl: './listausuarios.component.html',
  styleUrls: ['./listausuarios.component.css']
})
export class ListausuariosComponent implements OnInit {

  listaUsuarios : Usuario[] = []

  constructor(
    private _http: HttpClient,
    private _user : UserService,
    private _router: Router
  ) {}

  ngOnInit(): void {
    console.log(this._user.isLogged())
    this._http.get<Usuario[]>("http://localhost:8080/usuario/all",
        {
            responseType: "json"
      })
      .subscribe((data: Usuario[]) => {
        this.listaUsuarios = data;
      });
  }

  logOut(){
    this._user.clearUser()
    this._router.navigate(["/"])
  }
}
