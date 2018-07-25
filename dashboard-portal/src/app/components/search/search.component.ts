import { Component, OnInit } from '@angular/core';

import { LogService } from './../../service/log/log.service';
import { SearchService } from './../../service/search/search.service';

import { MenuItem } from '../../model/menu-item';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

    public domainSearch: string;
    public keywordSearch: string;

    public domainList: MenuItem[] = []; 
 
    public columnDefs: any[] = [];
    public rowData: any[] = [];
 
    constructor(private logService: LogService, private searchService: SearchService) { 
      
    }

    ngOnInit() {
      this.loadGridData();
    }


    loadGridData() : void {
      this.columnDefs = this.searchService.getColumnDefs();  
      this.rowData = this.searchService.loadGridData();
    }

    public domainSearchChange(): void {
      this.domainList = this.searchService.loadDomainValues(this.domainSearch);
      this.logService.logJson(this.domainList);
    } 

    public domainSearchSelected(item: MenuItem): void {
      this.domainSearch = item.label;
      this.domainList = [];
    }

}
