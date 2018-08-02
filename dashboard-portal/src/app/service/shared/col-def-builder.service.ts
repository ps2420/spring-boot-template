import { Injectable } from '@angular/core';
import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ColDefBuilderService {

   constructor() { }

   buildSearchGridColDef () : any {
      let columnDefs = [
         {
            headerName: 'Document',   field: 'document', 'downloadDocument' : true,
            cellRenderer : function(params) {
              if(params.value === '' || !params.value) {
                   return '<div></div>';
              }
              return '<div title="Click to download" style="cursor:pointer;">' + '<i class="fa fa-download" aria-hidden="true" (click)="downloadFile()"></i>&nbsp;<span title="Click to download">'+params.value+'</span></div>';
            }
          },
          {
            headerName: 'Page Number', field: 'pageNumber', 'width' : 130
          },
          {
            headerName: 'Content', field: 'parsedTbls',
            cellRenderer : function(params) {
              if(params.value === '' || !params.value) {
                   return '<div></div>';
              }
              return '<div title="Click to download" style="cursor:pointer;">' + '<i class="fa fa-download" aria-hidden="true" (click)="downloadFile()"></i>&nbsp;<span title="Click to download">'+params.value+'</span></div>';
            }
          },
          {
            headerName: 'Description', field: 'content', 'width' : 5000, 
            cellRenderer : function(params) {
              if(params.value === '' || !params.value) {
                   return '<div></div>';
              }
              return '<div data-toggle="tooltip" title="' + params.value + '"> ' + (params.value) + '</span></div>';
            }
          }
      ];
      return columnDefs;
    }

    buildDownloadGridColDef (): any {
        let columnDefs = [
           {headerName: 'Product',     field: 'product' },
           {
            headerName: 'Uploaded By', field: 'uploadedBy',
              cellRenderer : function(params) {
              if(params.value === '' || !params.value) {
                   return '<div></div>';
              }
              return '<div title="Address Book (TO DO..)" style="cursor:pointer;">' + '<i class="fa fa-address-card-o" aria-hidden="true"></i>&nbsp;&nbsp;' +
              '<span title="">'+params.value+'</span></div>';
              }
            },
            {
              headerName: 'Document',    field: 'document',
              cellRenderer : function(params) {
	              if(params.value === '' || !params.value) {
	                   return '<div></div>';
	              }
	              return '<div title="Click to download" style="cursor:pointer;">' + '<i class="fa fa-download" aria-hidden="true" (click)="downloadFile()"></i>&nbsp;<span title="Click to download">'+params.value+'</span></div>';
              }
            },
           { 

              headerName: 'Upload Date', field: 'uploadDate',
              cellRenderer : function(params) {
                if(params.value === '' || !params.value) {
                     return '<div></div>';
                }
                let pipe = new DatePipe('en-US');
                let formattedDate = pipe.transform(new Date(params.value), 'medium');
                return '<div><span title="">' + formattedDate + '</span></div>';
              }

           },
           {headerName: 'Comment',     field: 'comments'}
        ];
        return columnDefs;
    }
}





