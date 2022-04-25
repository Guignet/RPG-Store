import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tag} from "../models/tag";

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
}
