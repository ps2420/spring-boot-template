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
    
    this.uploader.onErrorItem = (item, response, status, headers) => this.onErrorItem(item, response, status, headers);
    
    this.uploader.onSuccessItem = (item, response, status, headers) => this.onSuccessItem(item, response, status, headers);
  }
 
 
  onSuccessItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): void {
    let data = JSON.parse(response); //success server response
    console.log("File has been uploaded successfully : " + response + "==>" + data);
  }

  onErrorItem(item: FileItem, response: string, status: number, headers: ParsedResponseHeaders): void {
    let error = JSON.parse(response); //error server response
    console.log("Error in file uploading " + error);
  }

 
  public fileOverBase(e:any):void {
    this.hasBaseDropZoneOver = e;
    console.log("is running for HTML 5" + this.uploader.isHTML5);
  }


}
