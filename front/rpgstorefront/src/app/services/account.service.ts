import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

const API_URL = 'http://localhost:8080/api/auth';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http:HttpClient) {



  }
}
