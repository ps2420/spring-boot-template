import { Injectable } from '@angular/core';

import { LeftNavChildMenu } from '../model/left-nav-child-menu';
import { LeftNavMenu } from '../model/left-nav-menu';

import { LogService } from './log/log.service';

@Injectable({
  providedIn: 'root'
})

export class HomePageService {

    constructor(private logService: LogService) { }

    loadMenuItems() : LeftNavMenu[] {
		var options = [];
		
		options.push(this.prepareMenuItem('Upload Document', 'leftNavUploadDocument', true));
		options.push(this.prepareMenuItem('Download Document', 'leftNavDownloadDocument', true));
		options.push(this.prepareMenuItem('Search Document', 'leftNavSearchDocument', true));

		return options;
	}

	prepareMenuItem(_label : string, _field : string, _selected: boolean) : LeftNavMenu {
        let childMenu = new LeftNavMenu();
        childMenu.label = _label;
        childMenu.field = _field;
        childMenu.selected = _selected; 
        childMenu.childMenuItems = this.loadChildMenuItemsArray(); 
        return childMenu;
	}

    loadChildMenuItemsArray() : LeftNavChildMenu[] {
        let childMenu : LeftNavChildMenu [] = [];
        childMenu.push(this.loadChildMenuItems('Equity Debt Ratio', 'leftNavEqdr', true)) 
        return childMenu;
    }

	loadChildMenuItems(_label : string, _field : string, _selected: boolean) : LeftNavChildMenu {
        let childMenu = new LeftNavChildMenu();
        childMenu.label = _label;
        childMenu.field = _field;
        childMenu.selected = _selected; 
        return childMenu;
	}
}
