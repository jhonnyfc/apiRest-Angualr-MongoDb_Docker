import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthvGuard implements CanActivate {

  constructor(
    private _user: UserService,
    private _router: Router
  ){
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (!this._user.isLogged()){
        alert("No tienes acceso")
        this._router.navigate(["/login"])
    } 
    return this._user.isLogged();
  }
  
}
