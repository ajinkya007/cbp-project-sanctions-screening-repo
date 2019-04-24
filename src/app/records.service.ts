import { Injectable } from '@angular/core';
import { Records } from './records';
import { Headers, Http } from '@angular/http';
import { MaliciousRecords } from './MaliciousRecords';
//import 'rxjs/add/operator/toPromise';

@Injectable()
export class RecordService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: Http) { }

  getRecords():  Promise<Records[]> {
    return this.http.get(this.baseUrl + '/records')
      .toPromise()
      .then(response => response.json() as Records[])
      .catch(this.handleError);
  }

  getValidRecords():  Promise<Records[]> {
    return this.http.get(this.baseUrl + '/validrecords')
      .toPromise()
      .then(response => response.json() as Records[])
      .catch(this.handleError);
  }

  getScreenedRecords():  Promise<Records[]> {
    return this.http.get(this.baseUrl + '/screenedrecords')
      .toPromise()
      .then(response => response.json() as Records[])
      .catch(this.handleError);
  }

  getMailciousRecords():Promise<MaliciousRecords[]>{
    return this.http.get(this.baseUrl + '/maliciousrecords')
      .toPromise()
      .then(response => response.json() as MaliciousRecords[])
      .catch(this.handleError);
  }

  createNewRecords(MaliciousRecords: MaliciousRecords[]): Promise<MaliciousRecords[]> {
    return this.http.post(this.baseUrl + '/mRecords', MaliciousRecords)
      .toPromise().then(response => response.json() as MaliciousRecords)
      .catch(this.handleError);
  }



  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }
}