import { Component, OnInit } from '@angular/core';
import { HomePageService } from '../../service/home-page.service';
import { LogService } from '../../service/log/log.service';

import { LeftNavChildMenu } from '../../model/left-nav-child-menu';
import { LeftNavMenu } from '../../model/left-nav-menu';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  providers: [] 
  
})

export class HomePageComponent implements OnInit {

    childMenuItems : LeftNavMenu [];

    title: String = 'Financial';

    constructor(private homePageService: HomePageService, private logService: LogService) {
        if(this.homePageService.getAppConfig()['app_name']) {
            this.title = this.homePageService.getAppConfig()['app_name'];
        }
    }

    ngOnInit() { 
       this.initMenuItems();
    }

    initMenuItems(): void {
        let _childMenuItems : LeftNavMenu[] = this.homePageService.loadMenuItems();
        this.childMenuItems = _childMenuItems; 
        //this.logService.logJson(this.childMenuItems);
     }
 
}
