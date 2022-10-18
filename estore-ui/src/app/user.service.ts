import { APP_ID, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpStatusCode } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersURL = 'http://localhost:8080/users';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  addUser(username: String, password: String): boolean {
    try {
      this.http.post<Object>(this.usersURL, {"username": username, "password": password}, this.httpOptions)
      // above line should return ResponseEntity<User>, not sure how to get status code out of it but
      // right now the request isn't making it to the back end at all
      return true
    } catch {
      return false;
    }
  }

}
