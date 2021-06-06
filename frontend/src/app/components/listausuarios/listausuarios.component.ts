import { HttpClient } from '@angular/common/http';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { Usuario } from 'src/app/utils/interfaces';

//  https://stackblitz.com/edit/ngfor-draw-update-ngmodel?file=src%2Fapp%2Fapp.component.html
// ejemplo a ver filtardad ode datos 

@Component({
  selector: 'app-listausuarios',
  templateUrl: './listausuarios.component.html',
  styleUrls: ['./listausuarios.component.css']
})
export class ListausuariosComponent implements OnInit {

  header = {
    "Access-Control-Allow-Origin": "*"
  }

  listaUsuarios : Usuario[] = []

  constructor(
    private _http: HttpClient,
    private _user : UserService,
    private _router: Router
  ) {
    this._user.updateLogStatus()
  }

  ngOnInit(): void {
    // console.log(this._user.isLogged())
    this.getLista()
  }

  logOut(){
    this._user.clearUser()
    this._router.navigate(["/login"])
  }

  borrar(id:String){
    this._http.get("http://localhost:8080/usuario/del/"+id
    , {withCredentials: true,responseType: "text", headers : this.header}
    ).toPromise().then( data => {
        alert("Eliminado con exito")
        this.getLista()
      },
      error => {
          alert(error.error)
    })
  }

  getLista(){
    this._http.get<Usuario[]>("http://localhost:8080/usuario/all",
    {withCredentials: true,responseType: "json", headers : this.header}
    ).subscribe((data: Usuario[]) => {
        this.listaUsuarios = data;
    },
    error =>{
      console.log(error.error)
    });
  }
}
