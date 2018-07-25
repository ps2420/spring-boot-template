import { Injectable } from '@angular/core';

import { LogService } from '../log/log.service';
import { HtmlContentService } from '../static-contents/html-content.service';

import { MenuItem } from './../../model/menu-item';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

    constructor(private logService: LogService, private http: HttpClient, 
        private htmlContentService: HtmlContentService) {

    }

    getColumnDefs() : any {
        return this.htmlContentService.getSearchDocumentGridColumDefs();
    }

    loadGridData(): any[] {
        let grid_data: any[] = [];
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', content: '.......', pageNumber: 1});
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', content: '.......', pageNumber: 2});
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', content: '.......', pageNumber: 100});
        return grid_data;
    }

    loadDomainValues(searchkey: string) : MenuItem[] {
        const dmainValues: MenuItem[] = this.htmlContentService.getFinancialDomainValues();;
        if(searchkey === '') {
           return tdmainValues; 
        }
        return dmainValues.filter(item =>  item.label.toLowerCase().indexOf(searchkey.toLowerCase()) >= 0);
    } 
}
