import { Component, OnInit } from '@angular/core';

import { AppContextService } from './service/shared/app-context.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    
    title = 'Financial Datalake Document Management System';
    app_name: String = 'Financial';
    app_context: any = {};

    ngOnInit() {
    
    }

    constructor(private appContextService: AppContextService) {
       this.app_context = this.appContextService.getAppContext();
       this.title = this.app_context['app_config']['page_title'];
       this.app_name = this.app_context['app_config']['app_name']; 
    }
}
