
  import { Component, OnInit } from '@angular/core';
  import { FormBuilder, FormGroup } from "@angular/forms";
  import { HttpClient } from '@angular/common/http';

  import {RecordService} from '../records.service';
  import {Records} from '../records';
  import {MaliciousRecords} from '../MaliciousRecords';
  import { FilesUploadedService } from '../files.service';
  import {Files} from '../files';

  @Component({
    selector: 'app-home-page',
    templateUrl: './home-page.component.html',
    styleUrls: ['./home-page.component.css'],
  })
  
  
  export class HomePageComponent implements OnInit {
  form: FormGroup;
  file: File;
  fileContent: string = ''; 

  msg:string = "File uploaded Successfully !!";

  records :Records[];
  mRecords : MaliciousRecords[];
  record :Records =  new Records();
  newRecords:Records[];

  files:Files[];


  displayRecords:Records[];


  constructor(private fb: FormBuilder, private http: HttpClient,private recordService:RecordService,private filesUploaded:FilesUploadedService) {}
  
  ngOnInit() {
      this.createForm();
  }
  
  // Instantiate an AbstractControl from a user specified configuration
    createForm() {
      this.form = this.fb.group({
        file_upload: null
      });
    }
  
    // Check for changes in files inputs via a DOMString reprsenting the name of an event
    fileChange(event: any) {
      // Instantiate an object to read the file content
      let reader = new FileReader();
      // when the load event is fired and the file not empty
      if(event.target.files && event.target.files.length > 0) {
        // Fill file variable with the file content
        this.file = event.target.files[0];
      }
    }
  
    // Upload the file to the API
    upload() {

      if(this.file!=null)
      alert("File Uploaded Sucessfully");
      else
      alert("Kindly upload some file");
      // Instantiate a FormData to store form fields and encode the file
      let body = new FormData();
      // Add file content to prepare the request
      body.append("file", this.file);
      // Launch post request
      this.http.post('http://localhost:8080/handleFileUpload', body)
      .subscribe(
        // Admire results
        (data) => {console.log(data)},
        // Or errors :-(
        error => console.log(error),
        // tell us if it's finished
        () => { console.log("completed") }
      );

    }

    getRecords()
    {
      console.log("Done"); 
      this.recordService.getRecords()
      .then(records=>this.records=records);

      // console.log(this.myHero);
     // console.log( this.records[0]);
    }

    getValidRecords()
    {
      this.recordService.getValidRecords()
      .then(records=>this.records=records);
    }

    screenRecords(){
      this.recordService.getValidRecords()
      .then(records=>this.records=records);

      this.recordService.getMailciousRecords()
      .then(mRecords=>this.mRecords=this.mRecords);

      let k=0;
      for(let i of this.records){
        for(let j of this.mRecords){

          if(i.payerName != j.payerName && i.payeeName!=j.payeeName)
          {
            this.newRecords[k].payeeName = i.payeeName;
            this.newRecords[k].payerName = i.payerName;
            this.newRecords[k].payerAccount = i.payerAccount;
            this.newRecords[k].payeeAccount = i.payeeAccount;
            this.newRecords[k].transactionRef = i.transactionRef;
            this.newRecords[k].valueDate = i.valueDate;
          }
          k++;
        }     
      }
  }

  getScreenedRecords()
  {
    this.recordService.getScreenedRecords()
    .then(records=>this.records=records);
  }

    getUploadedFiles()
    {
      this.filesUploaded.getFiles()
      .then(files=>this.files=files);
    }
  }

   