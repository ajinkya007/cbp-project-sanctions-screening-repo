import { Injectable } from '@angular/core';
import { Users } from './users';
import { Headers, Http } from '@angular/http';
//import 'rxjs/add/operator/toPromise';

@Injectable()
export class UserService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: Http) { }

  getUsers():  Promise<Users[]> {
    return this.http.get(this.baseUrl + '/api/Users/')
      .toPromise()
      .then(response => response.json() as Users[])
      .catch(this.handleError);
  }

  createUser(UsersData: Users): Promise<Users> {
    return this.http.post(this.baseUrl + '/api/Users/', UsersData)
      .toPromise().then(response => response.json() as Users)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }
}