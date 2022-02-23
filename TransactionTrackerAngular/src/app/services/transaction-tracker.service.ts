import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from '../models/transaction.model';
const baseUrl = "http://localhost:8080/api/transactions";
@Injectable({
  providedIn: 'root'
})
export class TransactionTrackerService {

  constructor(private http: HttpClient) { }
  getAll(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(baseUrl);
  }
  get(id: any): Observable<Transaction> {
    return this.http.get(`${baseUrl}/${id}`);
  }
  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  update(id: any, data: any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
  findByTitle(title: any): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${baseUrl}?title=${title}`);
  }
}
