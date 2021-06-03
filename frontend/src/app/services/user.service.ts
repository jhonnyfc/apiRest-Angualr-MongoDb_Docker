import { Injectable } from '@angular/core';
import { Usuario } from '../utils/interfaces';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  user: Usuario

  constructor() {
    this.user = {}
  }

  isLogged(){
    return !(Object.keys(this.user).length === 0)
  }

  clearUser(){
    this.user = {}
  }
}
