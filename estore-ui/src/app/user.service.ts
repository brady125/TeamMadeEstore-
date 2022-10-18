import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpStatusCode } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersURL = 'http://localhost:8080';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  addUser(username: String, password: String): boolean {
    // try {
    //   var result = this.http.post<User>(this.usersURL, {"username": username, "password": password}, this.httpOptions)
    //   return true;
    // } catch {
    //   return false;
    // }
    return false;
  }

}
