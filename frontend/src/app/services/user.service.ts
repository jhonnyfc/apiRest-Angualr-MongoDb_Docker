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
  IS_LOGGED_COOKIE: string = "IS_LOGGED_COOKIE"
  expTime:number = 120/(60*60*24)

  constructor(
    private _router: Router,
    private _http: HttpClient
  ) {
    this.user = {}
    this.updateLogStatus()
  }


  isLogged(){
    if (Cookie.get(this.IS_LOGGED_COOKIE) === "")
      this.updateLogStatus()

    return Cookie.get(this.IS_LOGGED_COOKIE) === "true"
  }

  async clearUser(){
    await this._http.get("http://localhost:8080/usuario/close",
    {withCredentials: true,responseType: "json", headers : this.header})
    .toPromise().then(
      data => {
        console.log(data)
      },
      error => {
        Cookie.deleteAll("/")
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
          Cookie.set(this.IS_LOGGED_COOKIE, String(true), this.expTime,"/")
          this._router.navigate(["/listausers"])
        },
        error => {
          alert(error.error)
        }
      )
  }

  updateLogStatus(){
    this._http.get("http://localhost:8080/usuario/islogged"
      , {withCredentials: true,responseType: "json", headers : this.header}
    ).toPromise().then( data => {
        console.log(data)
        this.user = data
        Cookie.set(this.IS_LOGGED_COOKIE, String(true), this.expTime,"/")
      },
        error => {
        Cookie.set(this.IS_LOGGED_COOKIE, String(false), this.expTime,"/")
        console.log(error.error)
        this.user = {}
    })
  }

  borrarUser(id:String){
    this._http.get("http://localhost:8080/usuario/del/"+id
      , {withCredentials: true,responseType: "text", headers : this.header}
    ).toPromise().then( data => {
        console.log(data)
        alert("Eliminado con exito")
      },
      error => {
          alert(error.error)
    })
  }
}
