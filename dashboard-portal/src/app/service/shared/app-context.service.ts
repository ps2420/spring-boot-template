import { Injectable } from '@angular/core';

import { MenuItem } from './../../model/menu-item';
import { LogService } from './log.service';

@Injectable({
  providedIn: 'root'
})
export class AppContextService {

    constructor(private logService: LogService) { }
  
    app_config_local: any = {
        'app_name'   : '', 
        'product_id' : 'eqdbr',
        'api_gateway': 'http://localhost:3083', 
        'mock_data'  : false ,
        'page_title' : 'Financial Datalake Document Management System'
    } ;

    APP_CONFIG: any  = this.app_config_local;

    api_config : any = {
        'doc-audit'           : this.buildUrl('/db-service/docaudit/list'),
        'upload-document'     : this.buildUrl('/file-handler/uploadFile'),
        'download-document'   : this.buildUrl('/file-handler/downloadFile'),
 
        'unique-document'     : this.buildUrl('/search-service/search/uniqueDocuments'),
        'search-document'     : this.buildUrl('/search-service/search/searchDocuments'),
        'document-count-info' : this.buildUrl('/search-service/search/documentCountInfo')
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
