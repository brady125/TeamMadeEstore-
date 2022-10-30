import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from "./product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private usersURL = 'http://localhost:8080/products';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  searchProducts(searchTerm: String): Observable<Product[]> {
    return this.http.get<Product[]>(this.usersURL+"/?text="+searchTerm, this.httpOptions);
  }

}
