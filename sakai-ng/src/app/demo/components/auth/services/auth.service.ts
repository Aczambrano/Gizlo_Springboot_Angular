import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { LoginRequesI, LoginResponseI } from '../interfaces/login.interface';
import { Observable } from 'rxjs';
import * as CryptoJS from 'crypto-js';
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _isAuthenticated = false;
  private _strUrl: string = environment.api.url;
  private _strKeyToken: string = environment.localStorage.keyToken;

  constructor(private _http: HttpClient) { }

  isAuthenticated(){
    return this.isAuthenticated;
  }

  authenticated(flag: boolean){
    this._isAuthenticated = flag;
  }

  login(body: LoginRequesI): Observable<LoginResponseI>{
    return  this._http.post<LoginResponseI>(`${this._strUrl}/security`, body);
  }


  saveToken(token: string){
    const encryToken = CryptoJS.AES.encrypt(
      token, 
      this._strKeyToken
    ).toString();
    localStorage.setItem(environment.localStorage.token, encryToken);
  }

  getToken(): string{
    var descripToken: string = '';
    const encryToken = localStorage.getItem(environment.localStorage.token);
    if(encryToken){
      descripToken = CryptoJS.AES.decrypt(
        encryToken,
        this._strKeyToken
      ).toString(CryptoJS.enc.Utf8);
    }
    return descripToken;
  }
}
