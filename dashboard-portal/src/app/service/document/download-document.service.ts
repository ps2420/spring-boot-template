import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { LogService } from '../log/log.service';
import { HtmlContentService } from '../static-contents/html-content.service';

import { MenuItem } from './../../model/menu-item';

@Injectable({
  providedIn: 'root'
})
export class DownloadDocumentService {

    const api_config : any;

    constructor(private logService: LogService, private http: HttpClient, 
        private htmlContentService: HtmlContentService) {
        this.api_config = this.htmlContentService.apiConfig();
    }

    apiConfig() : any {
       return this.api_config;
    }

    getAppConfig(): any {
      return this.htmlContentService.appConfig();
    }

    getColumnDefs() : any {
      return this.htmlContentService.getDownloadDocumentGridColumDefs();
    }

    loadGridDataFromServer(fproduct: string) : any {
      let url = this.api_config['doc-audit'] + "?product=" + fproduct;
      return this.http.get(url);
    }
  
    loadFinancialproducts() : any {
      let url = this.api_config['finance-product'];
      return this.http.get(url);
    }
}
