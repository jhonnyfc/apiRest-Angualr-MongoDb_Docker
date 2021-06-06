import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Cookie } from 'ng2-cookies';
import { ResModo } from '../utils/interfaces';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class MododataService {
  header = {
    "Access-Control-Allow-Origin": "*"
  }

  expTime:number = 65/(60*60*24)
  localStorageOn : boolean
  IS_LOCAL_ID: string = "IS_LOCAL_ID"

  constructor(
    private _user:UserService,
    private _http:HttpClient
  ) {
    this.localStorageOn = this.isLocalAcivate()
    this.updateStoreMode()
  }

  setLocalStorageMode(mode:boolean) {
    //this._user.clearUser()
    Cookie.set(this.IS_LOCAL_ID, String(mode), this.expTime,"/")
    this._http.post("http://localhost:8080/usuario/setmode",
      {"IS_LOCAL_ID" : mode},
      {withCredentials: true,responseType: "text", headers : this.header}
    ).toPromise().then( data => {
        console.log(data)
      },
        error => {
        Cookie.deleteAll()
        console.log(error.error)
    })
  
    this.localStorageOn = mode;
  }

  isLocalAcivate(){
    this.localStorageOn = Cookie.get(this.IS_LOCAL_ID) === "true";
    return this.localStorageOn
  }

  updateStoreMode(){
    this._http.get("http://localhost:8080/usuario/getmode",
      {withCredentials: true,responseType: "json", headers : this.header}
    ).toPromise().then( (data:ResModo) => {
        console.log(data+ "res ")
        this.localStorageOn = data.IS_LOCAL_ID!
      },
        error => {
        console.log(error.error)
    })
  }
}
