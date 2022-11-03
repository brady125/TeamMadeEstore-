import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from "./product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productsURL = 'http://localhost:8080/products';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(this.productsURL+"/"+id, this.httpOptions);
  }
}
