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

  searchProducts(searchTerm: String): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsURL+"/?text="+searchTerm, this.httpOptions);
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(this.productsURL+"/"+id, this.httpOptions);
  }

  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.productsURL, product, this.httpOptions);
  }

  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(this.productsURL, product, this.httpOptions);
  }
  
  deleteProduct(id: number): Observable<Product> {
    return this.http.delete<Product>(this.productsURL+"/"+id, this.httpOptions);
  }

}
