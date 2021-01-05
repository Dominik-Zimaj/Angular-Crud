import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable} from "rxjs";

const baseUrl = "http://localhost:8080/api/person";

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(baseUrl);
  }

  get(id: any): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }

  update(id: any, data:any): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(`${baseUrl}`);
  }

  findByFirstName(firstname: any): Observable<any> {
    return this.http.get(`${baseUrl}/firstname/?firstName=${firstname}`);
  }

  findByLastName(lastName: any): Observable<any> {
    return this.http.get(`${baseUrl}/lastname/?lastName=${lastName}`);
  }
  
}
