import { Component, OnInit } from '@angular/core';
import { FileSelectDirective, FileUploader } from 'ng2-file-upload/ng2-file-upload';


import { LogService } from './../../service/shared/log.service';
import { DocumentService } from './../../service/shared/document.service';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  public title: String = "Equity Debt Ratio File Upload";
  public hasBaseDropZoneOver:boolean = false; 
 
  public uploader:FileUploader;

  app_context: any = {};

  constructor(private logService: LogService, private documentService: DocumentService) { 
    this.app_context = this.documentService.getAppContext();
    this.uploader = new FileUploader({
      url: this.app_context['api_config']['upload-document']
    });
  }

  ngOnInit() : void { 
    /**
    this.uploader.onBuildItemForm = (fileItem, form) => {
       form.append('another_field', 'another_value');
       return {fileItem, form};
    };
    **/

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
  }

}

