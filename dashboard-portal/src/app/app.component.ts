import { Component } from '@angular/core';

import { HtmlContentService } from './service/static-contents/html-content.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    
    title = 'Financial Datalake Document Management System';

    ngOnInit() {
    }

    constructor(private htmlContentService: HtmlContentService) {
       this.title = this.htmlContentService.appConfig()['page_title'];
    }
}
