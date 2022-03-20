import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService} from '@auth0/angular-jwt';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenStorageService } from "./token-storage.service";

const AUTH_API = 'http://localhost:8080/api/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient,
              private jwtHelper: JwtHelperService,
              private tokenStorage: TokenStorageService) { }
  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username,
      password
    }, httpOptions);
  }

  register(username: string, email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username,
      email,
      password
    }, httpOptions);
  }

  public isAuthenticated(): boolean {
      const token = this.tokenStorage.getToken();
      return !this.jwtHelper.isTokenExpired(token);
  }

}
