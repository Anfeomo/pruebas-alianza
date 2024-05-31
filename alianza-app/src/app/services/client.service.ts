import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Client } from "../models/client.model";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseUrl: string = 'http://localhost:8080/Clients';

  constructor(private http: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseUrl);
  }

  getClient(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.baseUrl}/${id}`);
  }

  createClient(Client: Client): Observable<Client> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Client>(this.baseUrl, Client, { headers });
  }

  updateClient(id: number, Client: Client): Observable<Client> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Client>(`${this.baseUrl}/${id}`, Client, { headers });
  }

  deleteClient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
