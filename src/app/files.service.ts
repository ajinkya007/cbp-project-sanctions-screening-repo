import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import { Files } from './files';
//import 'rxjs/add/operator/toPromise';

@Injectable()
export class FilesUploadedService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: Http) { }

  getFiles():  Promise<Files[]> {
    return this.http.get(this.baseUrl + '/uploadFiles')
      .toPromise()
      .then(response => response.json() as Files[])
      .catch(this.handleError);
  }

  
  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }
}