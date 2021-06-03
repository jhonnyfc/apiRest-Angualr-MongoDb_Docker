import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MododataService } from 'src/app/services/mododata.service';
import { UserService } from 'src/app/services/user.service';
import { Usuario } from 'src/app/utils/interfaces';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  singInForm : FormGroup

  constructor(
    public _strogaType: MododataService,
    private _user : UserService,
    private _builder : FormBuilder,
    private _http: HttpClient,
  ) {
    this.singInForm = this._builder.group({
      id: ['',Validators.required],
      password: ['',Validators.compose([Validators.required])]
    })
  }

  ngOnInit(): void {
    // if (this._strogaType.localStroageAcivate())

  }

  loginUsuario(values:Usuario){
    // console.log(values+"  "+this._user.isLogged());
    this._http.post("http://localhost:8080/usuario/login",
    values, {responseType: "json",
      //header},
    })
      .subscribe(
        data => {
          this._user.user = data
          alert("Acceso hecho con exito")
          // this._router.navigate(["/perfil"])
          // console.log(this._user.user)
          // console.log(this._user.isLogged());
          // this._user.setFoto()
        },
        error => {
          alert(error.error)
        }
      )
  }
  
  
  eventCheck(event:boolean){
    this._strogaType.setLocalStorageMode(event)
    console.log(this._strogaType.isLocalAcivate())
  }
}
