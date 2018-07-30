import { Injectable } from '@angular/core';

import { LogService } from './log.service';
import { AppContextService } from './app-context.service';

import { Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';

import { HttpClient, HttpHeaders } from '@angular/common/http';

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
 
    loadFinancialProdutsAudit(fproduct: string) : any {
        let url = this.app_context['api_config']['doc-audit'] + "/" + fproduct;
        return this.http.get(url);
    }
  
    loadFinancialproducts() : any {
        let url = this.app_context['api_config']['finance-product'];
        return this.http.get(url);
    }

    downloadFile(filename: string): Observable<any> {
        let url = this.app_context['api_config']['download-document'] + '?fileName=test.zip'; 
        return this.http.get(url, {
           responseType: "blob"
        });
    } 
}











