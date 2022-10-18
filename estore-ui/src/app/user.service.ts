import { APP_ID, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpStatusCode } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { NONE_TYPE } from '@angular/compiler';
import { User } from './user'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersURL = 'http://localhost:8080/users';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  addUser(username: String, password: String): Observable<Object> {
    return this.http.post<Object>(this.usersURL, {"username": username, "password": password}, this.httpOptions)
  }
}
