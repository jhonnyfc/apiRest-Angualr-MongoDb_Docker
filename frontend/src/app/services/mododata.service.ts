import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class MododataService {
  header = {
    "Access-Control-Allow-Origin": "*"
  }

  localStorageOn : boolean

  constructor(
    private _user:UserService,
    private _http:HttpClient
  ) {
    this.localStorageOn = false
  }

  async setLocalStorageMode(mode:boolean) {
    this._user.clearUser()
    await this._http.get("http://localhost:8080/usuario/setmode"
      , {withCredentials: true,responseType: "json", headers : this.header}
    ).toPromise().then( data => {
        console.log(data)
      },
        error => {
        console.log(error.error)
    })
  
    this.localStorageOn = mode;
  }

  isLocalAcivate(){
    return this.localStorageOn === true;
  }
}
