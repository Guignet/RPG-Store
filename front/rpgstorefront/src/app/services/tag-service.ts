import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tag} from "../models/tag";
import {CreateProduct} from "../models/create-product";
import {Product} from "../models/product";
import {CreateTag} from "../models/create-tag";

@Injectable({
  providedIn: 'root'
})
export class TagService {

  private BASE_URL = "http://localhost:3000";

  private httpOptions = {
    headers: new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
    })
  };

  constructor(private http : HttpClient) { }

  fetchAll(): Observable<Tag[]> {
    return this.http.get<Tag[]>(`${this.BASE_URL}/tags`);
  }

  create(tag: CreateTag): Observable<Tag[]> {
    return this.http.post<Tag[]>(`${this.BASE_URL}/tags`, tag, this.httpOptions);
  }

  getProduct(id: number): Observable<Tag>{
    return this.http.get<Tag>(`${this.BASE_URL}/tags/${id}`);
  }

  delete(id: number):void{
    this.http.delete(`${this.BASE_URL}/tags/${id}`).subscribe();
  }

  update(id: number, tag: Tag):void{
    this.http.put(`${this.BASE_URL}/tags/${id}`, tag, this.httpOptions).subscribe();
  }
}
