import { Injectable } from '@angular/core';

import { MenuItem } from './../../model/menu-item';

@Injectable({
  providedIn: 'root'
})
export class HtmlContentService {

    constructor() { }

    getSearchDocumentGridColumDefs () : any {
	    let columnDefs = [
	       {headerName: 'Domain',      field: 'domain' },
	       {headerName: 'Document',    field: 'document'},
	       {headerName: 'Description', field: 'content'},
	       {headerName: 'Page Number', field: 'pageNumber'}
	    ];
	    return columnDefs;
    }

    getSearchDocumentGridMockData(): any[] {
        let grid_data: any[] = [];
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', content: '.......', pageNumber: 1});
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', content: '.......', pageNumber: 2});
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', content: '.......', pageNumber: 100});
        return grid_data;
    }

    getDownloadDocumentGridColumDefs(): any {
        let columnDefs = [
           {headerName: 'Domain',      field: 'domain' },
           {headerName: 'Uploaded By', field: 'uploadedBy'},
           {headerName: 'Document',    field: 'document'},
           {headerName: 'Upload Date', field: 'uploadDate'}
        ];
        return columnDefs;
    }

    getDownloadDocumentGridMockData(): any[] {
        let grid_data: any[] = [];
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', uploadedBy: 'amaris', uploadDate: new Date()});
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', uploadedBy: 'amaris', uploadDate: new Date()});
        grid_data.push({domain: 'Equity Debt Ratio', document: 'random_document.pdf', uploadedBy: 'amaris', uploadDate: new Date()});
        return grid_data;
    }

    getFinancialDomainValues(): MenuItem[] {
        let domain_data: any[] = [];
        domain_data.push(this.prepareMenuItem('Equity', '_equity', false));
        domain_data.push(this.prepareMenuItem('Derivatives', 'derivatives', false));
        domain_data.push(this.prepareMenuItem('Options', 'options', false)); 
        return domain_data;
    }

    prepareMenuItem(_label : string, _field : string, _selected: boolean) : MenuItem {
        let childMenu = new MenuItem();
        childMenu.label = _label;
        childMenu.field = _field;
        childMenu.selected = _selected;
        return childMenu;
    }
}
