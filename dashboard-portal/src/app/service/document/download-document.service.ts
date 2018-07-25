import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { LogService } from '../log/log.service';
import { HtmlContentService } from '../static-contents/html-content.service';

import { MenuItem } from './../../model/menu-item';

@Injectable({
  providedIn: 'root'
})
export class DownloadDocumentService {

    constructor(private logService: LogService, private http: HttpClient, 
        private htmlContentService: HtmlContentService) {

    }

    getColumnDefs() : any {
        return this.htmlContentService.getDownloadDocumentGridColumDefs();
    }

    loadGridData(): any[] {
        return this.htmlContentService.getDownloadDocumentGridMockData();
    }

    loadDomainValues(searchkey: string) : MenuItem[] {
        const domainValues: MenuItem[] = this.htmlContentService.getFinancialDomainValues();;
        if(searchkey === '') {
           return domainValues; 
        }
        return domainValues.filter(item =>  item.label.toLowerCase().indexOf(searchkey.toLowerCase()) >= 0);
    } 
}
