import { Component, OnInit } from '@angular/core';
import { FileSelectDirective, FileUploader } from 'ng2-file-upload/ng2-file-upload';

@Component({
  selector: 'app-upload-document',
  templateUrl: './upload-document.component.html',
  styleUrls: ['./upload-document.component.css']
})

export class UploadDocumentComponent implements OnInit {

  public title: String = "Equity Debt Ratio File Upload";
  public hasBaseDropZoneOver:boolean = false; 
 
 
  public uploader:FileUploader = new FileUploader({
      url: "http://localhost:5017/uploadSingleFile"
  });
 

  constructor() { }

   ngOnInit() : void {
    
    //this.uploader.onErrorItem = (item, response, status, headers) => this.onErrorItem(item, response, status, headers);
    
    //this.uploader.onSuccessItem = (item, response, status, headers) => this.onSuccessItem(item, response, status, headers);
  }
 
  
  public changeDnDBorderColor(type: string, e:any): void {
    this.hasBaseDropZoneOver = (type === 'mouseleave') ? false : true;
  }
 
  public fileOverBase(e:any):void {
    this.hasBaseDropZoneOver = e;
    console.log("fileoverBASE....")
  }


}
