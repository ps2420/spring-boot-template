import { Component, OnInit } from '@angular/core';
import { FileSelectDirective, FileUploader } from 'ng2-file-upload/ng2-file-upload';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  public title: String = "Equity Debt Ratio File Upload";
  public hasBaseDropZoneOver:boolean = false; 
 
  URL: string = "http://localhost:5013/file-upload/uploadSingleFile";

  public domainValue: String = "PK";
  
  public uploader:FileUploader = new FileUploader({
      url: this.URL,
      additionalParameter: {
         domain: this.domainValue
      }
  });
 
  constructor() { 
     
  }

   ngOnInit() : void { 

    this.uploader.onBuildItemForm = (fileItem, form) => {
       form.append('another_field', 'another_value');
       return {fileItem, form};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => this.onErrorItem(item, response, status, headers);
    
    this.uploader.onSuccessItem = (item, response, status, headers) => this.onSuccessItem(item, response, status, headers);
  }
 

  public onSuccessItem (item: any, response: string, status: number, headers: any) : void {
     console.log("File has been uploaded successfully..")
  }

  public onErrorItem (item: any, response: string, status: number, headers: any) : void {
     console.log("Error in uploading the file item..")
  }
  
  public changeDnDBorderColor(type: string, e:any): void {
     this.hasBaseDropZoneOver = (type === 'mouseleave') ? false : true;
  }
 
  public fileOverBase(e:any):void {
    this.hasBaseDropZoneOver = e;
    console.log("fileoverBASE....")
  }

}

