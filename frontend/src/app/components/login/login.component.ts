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
  switchActivo : boolean

  constructor(
    public _strogaType: MododataService,
    private _user : UserService,
    private _builder : FormBuilder
  ) {
    this.singInForm = this._builder.group({
      id: ['',Validators.required],
      password: ['',Validators.compose([Validators.required])]
    })
    this.switchActivo = true
  }

  ngOnInit(): void {
    // if (this._user.isLogged())
    //   this._router.navigate(["/listausers"])
    this._strogaType.isLocalAcivate()
  }

  loginUsuario(values:Usuario){
    this._user.loginUsuario(values)
  }
  
  
  eventCheck(event:boolean){
    this.switchActivo = false
    this._strogaType.setLocalStorageMode(event)
    // console.log(this._strogaType.isLocalAcivate())
    this.switchActivo = true
  }

  // estaLog(){
  //   this._strogaType.isLocalAcivate()
  // }
}
