import { Injectable } from '@angular/core';

import { LogService } from '../log/log.service';
import { HtmlContentService } from '../static-contents/html-content.service';

import { MenuItem } from './../../model/menu-item';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

    const api_config : any = {} ;

    constructor(private logService: LogService, private http: HttpClient, 
        private htmlContentService: HtmlContentService) {
        this.api_config = this.htmlContentService.apiConfig();
    }

    getColumnDefs(): any {
        return this.htmlContentService.getSearchDocumentGridColumDefs();
    }

    getAppConfig(): any {
        return this.htmlContentService.appConfig();
    }

    loadGridData(product: string, keyword: string) : any[] {
        let url = this.api_config['search-document'] + "?product=" + product + "&keyword=" + keyword;
        return this.http.get(url);
    }
 
    loadFinancialproducts() : any {
        let url = this.api_config['finance-product'];
        return this.http.get(url);
    }
}
