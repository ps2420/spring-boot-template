import { Injectable } from '@angular/core';

import { LogService } from '../shared/log.service';
import { AppContextService } from '../shared/app-context.service';

import { DocumentService } from '../shared/document.service';

import { MenuItem } from './../../model/menu-item';
import { HttpClient } from '@angular/common/http';

import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
 
    app_context: any = {};

    constructor(private logService: LogService, private http: HttpClient, 
        private appContextService: AppContextService, private documentService: DocumentService) {
        this.app_context = this.appContextService.getAppContext();
    }
 
    getAppContext(): any {
        return this.app_context;
    }

    loadGridData(product: string, keyword: string) : any {
        let url = this.app_context['api_config']['search-document'] + "?product=" + product + "&keyword=" + keyword;
        return this.http.get(url);
    }
 
    loadFinancialproducts() : any {
        let url = this.app_context['api_config']['finance-product'];
        return this.http.get(url);
    }

    downloadFile(filename: string) : void {
        this.documentService.downloadFile(filename);
    }

    listFilesByProduct(product: string) : Observable<any> {
        this.logService.log("Inside listFilesByProduct function..");
        let url = this.app_context['api_config']['file_by_product'] + '?product=' + product;
        return this.http.get(url);
    }

    getSearchDocumentGridColumDefs () : any {
        let columnDefs = [
         {
            headerName: 'Product',    field: 'product' },
          {
            headerName: 'Document',   field: 'document', 'downloadDocument' : true,
            cellRenderer : function(params) {
              if(params.value === '' || !params.value) {
                   return '<div></div>';
              }
              return '<div title="Click to download" style="cursor:pointer;">' + '<i class="fa fa-download" aria-hidden="true" (click)="downloadFile()"></i>&nbsp;<span title="Download">'+params.value+'</span></div>';
            }
          },
          {
            headerName: 'Description', field: 'content'
          },
          {
            headerName: 'Page Number', field: 'pageNumber'
          }
        ];
        return columnDefs;
    }
}





