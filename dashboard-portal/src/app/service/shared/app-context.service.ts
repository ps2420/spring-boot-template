import { Injectable } from '@angular/core';

import { MenuItem } from './../../model/menu-item';
import { LogService } from './log.service';

@Injectable({
  providedIn: 'root'
})
export class AppContextService {

    constructor(private logService: LogService) { }
  
    app_config_local: any = {
        'app_name'   : 'Debt Equity Ratio', 
        'api_gateway': 'http://localhost:5013', 
        'mock_data'  : false ,
        'page_title' : 'Financial Datalake Document Management System'
    } ;

    APP_CONFIG: any  = this.app_config_local;

    api_config : any = {
       'finance-product'   : this.buildUrl('/web-api/html/financeProducts'),
       'search-document'   : this.buildUrl('/web-api/search/documents'),
       'file_by_product'   : this.buildUrl('/web-api/html/files'),
       
       'doc-audit'         : this.buildUrl('/web-api/docaudit/list'),
       'search-grid'       : this.buildUrl('/search-service/..'),
       
       'upload-document'   : this.buildUrl('/file-handler/uploadFile'),
       'download-document' : this.buildUrl('/file-handler/downloadFile')
    }

    getAppContext(): any {
        let context = {
          'api_config'   : this.api_config,
          'app_config'   : this.APP_CONFIG
        };
        return context;
    }

    getUrl(url_param: string): string {
        return this.api_config[url_param];
    }

    buildUrl(url: string): string { 
       return this.APP_CONFIG.api_gateway + url;
    } 
  
    prepareMenuItem(_label : string, _field : string, _selected: boolean) : MenuItem {
        let childMenu = new MenuItem();
        childMenu.label = _label;
        childMenu.field = _field;
        childMenu.selected = _selected;
        return childMenu;
    }
}
