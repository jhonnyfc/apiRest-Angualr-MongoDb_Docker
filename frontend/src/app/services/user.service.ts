import { HttpClient } from '@angular/common/http';
import { ThisReceiver } from '@angular/compiler';
import { Injectable, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Usuario } from '../utils/interfaces';

@Injectable({
  providedIn: 'root'
})

export class UserService{
  
  header = {
    "Access-Control-Allow-Origin": "*"
  }

  user: Usuario

  constructor(
    private _router: Router,
    private _http: HttpClient
  ) {
    this.user = {}
    this._http.get("http://localhost:8080/usuario/islogged"
      , {withCredentials: true,responseType: "json", headers : this.header}
    )
    .subscribe(
      (data: Usuario) => {
        console.log(data)
        this.user = data
      },
      error => {
        console.log(error.error)
    })
  }


  isLogged(){
    this._http.get("http://localhost:8080/usuario/islogged"
      , {withCredentials: true,responseType: "json", headers : this.header}
    ).toPromise().then( data => {
        console.log(data)
        this.user = data
      },
        error => {
        console.log(error.error)
        this.user = {}
    })

    console.log(!(Object.keys(this.user).length === 0)+" nice")
    return !(Object.keys(this.user).length === 0)
  }

  async clearUser(){
    await this._http.get("http://localhost:8080/usuario/close",
    {withCredentials: true,responseType: "json", headers : this.header})
    .toPromise().then(
      data => {
        console.log(data)
      },
      error => {
        console.log(error.error)
    })
    this.user = {}
  }

  loginUsuario(values:Usuario){
    this._http.post("http://localhost:8080/usuario/login",values,
      {withCredentials: true,responseType: "json", headers : this.header}
    )
      .subscribe(
        data => {
          console.log(data)
          this.user = data
          alert("Acceso hecho con exito")
          this._router.navigate(["/listausers"])
        },
        error => {
          alert(error.error)
        }
      )
  }
}
