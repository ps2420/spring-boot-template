import { Injectable } from '@angular/core';

import { MenuItem } from './../../model/menu-item';
import { LogService } from '../log/log.service';

@Injectable({
  providedIn: 'root'
})
export class HtmlContentService {

    constructor(private logService: LogService) { }
  
    const app_config_local = {
        'app_name'   : 'Debt Equity Ratio', 
        'api_gateway': 'http://localhost:5013', 
        'mock_data'  : false ,
        'page_title' : 'Financial Datalake Document Management System'
    } ;

    const APP_CONFIG: any  = (app_config != null) ? app_config : this.app_config_local;

    const api_config : any = {
       'finance-product'   : this.buildUrl('/web-api/html/financeProducts'),
       'search-document'   : this.buildUrl('/web-api/search/documents'),
       
       'doc-audit'         : this.buildUrl('/web-api/docaudit/list'),
       'search-grid'       : this.buildUrl('/search-service/..'),
       
       'upload-document'   : this.buildUrl('/file-handler/uploadFile'),
       'download-document' : this.buildUrl('/file-handler/download')
    }

    buildUrl(url: string): string { 
       return this.APP_CONFIG.api_gateway + url;
    }

    apiConfig(): any { 
       return this.api_config;
    }

    appConfig(): any {
       return this.APP_CONFIG;
    }

    getSearchDocumentGridColumDefs () : any {
	    let columnDefs = [
	       {headerName: 'Product',     field: 'product' },
	       {headerName: 'Document',    field: 'document'},
	       {headerName: 'Description', field: 'content'},
	       {headerName: 'Page Number', field: 'pageNumber'}
	    ];
	    return columnDefs;
    }
 
    getDownloadDocumentGridColumDefs(): any {
        let columnDefs = [
           {headerName: 'Product',     field: 'product' },
           {headerName: 'Uploaded By', field: 'uploadedBy'},
           {headerName: 'Document',    field: 'document'},
           {headerName: 'Upload Date', field: 'uploadDate'},
           {headerName: 'Comment',     field: 'comments'}
        ];
        return columnDefs;
    }
 
    prepareMenuItem(_label : string, _field : string, _selected: boolean) : MenuItem {
        let childMenu = new MenuItem();
        childMenu.label = _label;
        childMenu.field = _field;
        childMenu.selected = _selected;
        return childMenu;
    }
}
