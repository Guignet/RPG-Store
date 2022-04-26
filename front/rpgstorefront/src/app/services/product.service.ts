import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../models/product";
import {CreateProduct} from "../models/create-product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private BASE_URL = "http://localhost:8080/api/auth";

  private httpOptions = {
    headers: new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    })
  };

  constructor(private http : HttpClient) { }

  fetchAll(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.BASE_URL}/products`);
  }

  create(product: CreateProduct): Observable<Product[]> {
    return this.http.post<Product[]>(`${this.BASE_URL}/products`, product, this.httpOptions);
  }

  getProduct(id: number): Observable<Product>{
    return this.http.get<Product>(`${this.BASE_URL}/products/${id}`);
  }

  delete(id: number):void{
    this.http.delete(`${this.BASE_URL}/products/${id}`).subscribe();
  }

  update(id: number, product: Product):void{
    this.http.put(`${this.BASE_URL}/products/${id}`, product, this.httpOptions).subscribe();
  }
}
