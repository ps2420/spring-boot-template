import { Injectable } from '@angular/core';

import { LogService } from '../shared/log.service';
import { AppContextService } from '../shared/app-context.service';

import { MenuItem } from './../../model/menu-item';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
 
    app_context: any = {};

    constructor(private logService: LogService, private http: HttpClient, 
        private appContextService: AppContextService) {
        this.app_context = this.appContextService.getAppContext();
    }

    getColumnDefs(): any {
        return this.appContextService.getSearchDocumentGridColumDefs();
    }

    getAppContext(): any {
        return this.app_context;
    }

    loadGridData(product: string, keyword: string) : any{
        let url = this.app_context['api_config']['search-document'] + "?product=" + product + "&keyword=" + keyword;
        return this.http.get(url);
    }
 
    loadFinancialproducts() : any {
        let url = this.app_context['api_config']['finance-product'];
        return this.http.get(url);
    }
}
