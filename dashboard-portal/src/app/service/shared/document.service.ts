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

    downloadFile(filename: string): void {
        let url = this.app_context['api_config']['download-document'] + '?fileName=test.zip'; 
        this.getFile(url).subscribe(fileData => {
            let b:any = new Blob([fileData], { type: 'application/zip' });
            var url= window.URL.createObjectURL(b);
              window.open(url);
            }
        );
    }

    getFile(path: string): Observable<any> {
        let headers = new HttpHeaders();
        headers = headers.set('Accept', 'application/zip');
        return this.http.get(path, { headers: headers, responseType: 'blob' })
            .map((response: Response) => response );
    }
}











