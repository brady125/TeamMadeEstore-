import { APP_ID, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpResponse, HttpStatusCode } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { User } from './user';
import { tap } from 'rxjs';

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

  getCart(username: String): Observable<User>{  //should return an Observable<Product[]>
    return this.http.get<User>(this.usersURL+"/shopping-cart/"+username, this.httpOptions);
      // it doesn't seem like anyone made a back end way to get the shopping cart, can probably get someone else to do it
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(this.usersURL+"/"+id, this.httpOptions);
  }
}
