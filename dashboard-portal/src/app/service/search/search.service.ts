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

    loadGridData(document: string, keyword: string) : any { 
        let url = this.app_context['api_config']['search-document'] + "?document=" + document + "&keyword=" + keyword;
        this.logService.log("loading grid-data from url : " + url);
        return this.http.get(url); 
    }
  
    downloadFile(filename: string): Observable<any> {
        return this.documentService.downloadFile(filename);
    }

    listFilesByProduct(product: string) : Observable<any> {
        let url = this.app_context['api_config']['unique-document'];
        this.logService.log("search-service loading data from url : " + url);
        return this.http.get(url);
    }

    
}





