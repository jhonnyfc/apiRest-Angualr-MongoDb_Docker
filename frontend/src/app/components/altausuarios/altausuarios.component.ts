import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Usuario } from 'src/app/utils/interfaces';
import {Location} from '@angular/common';

@Component({
  selector: 'app-altausuarios',
  templateUrl: './altausuarios.component.html',
  styleUrls: ['./altausuarios.component.css']
})
export class AltausuariosComponent implements OnInit {

  userSignUp : FormGroup

  constructor(
    private _location: Location,
    private _builder : FormBuilder,
    private _http: HttpClient,
  ) {
    this.userSignUp = this._builder.group({
      id :  ['',Validators.required],
      nombre :  ['',Validators.required],
      edad :  ['',Validators.required],
      password :  ['',Validators.required]
    })
  }

  header = {
    "Access-Control-Allow-Origin": "*"
  }

  ngOnInit(): void {
  }

  altaUsuario(user:Usuario){
    this._http.post("http://localhost:8080/usuario/save", user,
      {withCredentials: true,responseType: "json", headers : this.header})
    .subscribe(
      data => {
        console.log(data)
        alert("Alta hecha con exito")
      },
      error => {
        alert(error.error)
      }
    )
  }

  backClicked() {
    this._location.back();
  }
}
