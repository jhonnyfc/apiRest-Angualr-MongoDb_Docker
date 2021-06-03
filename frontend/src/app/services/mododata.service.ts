import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MododataService {

  localStorageOn : boolean

  constructor() {
    this.localStorageOn = false
  }

  setLocalStorageMode(mode:boolean){
    this.localStorageOn = mode;
  }

  isLocalAcivate(){
    return this.localStorageOn === true;
  }
}
