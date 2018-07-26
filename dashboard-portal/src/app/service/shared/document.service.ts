import { Injectable } from '@angular/core';

import { LogService } from './log.service';
import { AppContextService } from './app-context.service';


import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DocumentService {
 
    app_context: any = {};

    constructor(private appContextService: AppContextService, private logService: LogService,
         private http: HttpClient) {
       this.app_context = this.appContextService.getAppContext();
    }

    getAppContext(): any {
       return this.app_context;
    }

    getColumnDefs() : any {
        return this.appContextService.getDownloadDocumentGridColumDefs();
    }

    loadFinancialProdutsAudit(fproduct: string) : any {
        let url = this.app_context['api_config']['doc-audit'] + "?product=" + fproduct;
        return this.http.get(url);
    }
  
    loadFinancialproducts() : any {
        let url = this.app_context['api_config']['finance-product'];
        return this.http.get(url);
    }
}
