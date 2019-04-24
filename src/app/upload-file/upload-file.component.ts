import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-upload-file',
  templateUrl: './upload-file.component.html',
  styleUrls: ['./upload-file.component.css']
})
export class UploadFileComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };
  i = 0; 

  constructor() { }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  ngOnInit() {
    this.progress.percentage = 0;
  }

                    //  set your counter to 1

 myLoop () {           //  create a loop function
   setTimeout(function () {    //  call a 3s setTimeout when the loop is called
    console.log("Hello");          //  your code here
      this.i++;                     //  increment the counter
      if (this.i < 100) {            //  if the counter < 10, call the loop function
         this.myLoop();             //  ..  again which will trigger another 
      }                        //  ..  setTimeout()
   }, 3000)
}

  upload($event) {
    var i= 0;
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);

    console.log("Filepath : " +this.currentFileUpload.name);

    //var fullPath = Request.Form.Files["currentFileUpload"].FileName;

    // var file:File = $event.files[0];


    for(i ; i<100;i++){
    setTimeout(() => {
    this.progress.percentage ++;
    }, 3000);

      console.log(this.progress.percentage);
    }

    //this.myLoop();

    //  this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(
    //    event => {
    //    if (event.type === HttpEventType.UploadProgress) {
    //      this.progress.percentage = Math.round(100 * event.loaded / event.total);
    //    }
    //  else if (event instanceof HttpResponse) {
    //      console.log('File is completely uploaded!');
    //  }
    //  });

    this.selectedFiles = undefined;
  }
}

