import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { LogService } from '../log/log.service';
import { HtmlContentService } from '../static-contents/html-content.service';

@Injectable({
  providedIn: 'root'
})
export class UploadDocumentService {
 
    const api_config : any;

    constructor(private logService: LogService, private http: HttpClient, 
        private htmlContentService: HtmlContentService) {
        this.api_config = this.htmlContentService.apiConfig();
    }

    apiConfig() : any {
       return this.api_config;
    }
}
