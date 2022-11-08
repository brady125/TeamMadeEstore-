import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersURL = 'http://localhost:8080/users';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.usersURL, user, this.httpOptions);
  }

  loginUser(username: String, password: String): Observable<User> {
    return this.http.post<User>(this.usersURL+"/check", {"username": username, "password": password}, this.httpOptions);
  }

  getUser(username: String): Observable<User> {
    return this.http.get<User>(this.usersURL+"/"+username, this.httpOptions);
  }
  
  updateUser(user: User): Observable<User> {
    return this.http.put<User>(this.usersURL, user, this.httpOptions);
  }
}
