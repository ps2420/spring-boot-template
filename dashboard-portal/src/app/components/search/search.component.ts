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

    public productSearch: string;
    public keywordSearch: string;

    public financialProducts: any[] = []; 
    public financialProducts_data: any[] = []; 
 
    public columnDefs: any[] = [];
    public rowData: any[] = [];
 
    constructor(private logService: LogService, private searchService: SearchService) { 
      this.productSearch = this.searchService.getAppConfig()['app_name'];
    }

    ngOnInit() {
      this.loadFinancialproducts();
      this.loadGridData();
    }

    public financialProductSelected(item: MenuItem): void {
      this.productSearch = item.label;
      this.financialProducts = this.filterFinancialProducts();
    }

    public filterFinancialProducts(): any[] {
      this.logService.log("selected input value : " + this.productSearch);
      if(this.productSearch === '') {
        this.financialProducts = this.financialProducts_data;
      } else {
        this.financialProducts = this.financialProducts_data.filter(item => {
          return item.label.toLowerCase().indexOf(this.productSearch.toLowerCase()) >= 0; 
        });
        this.logService.logJson(this.financialProducts);
      }
    }

    loadGridData() : void {
      this.columnDefs = this.searchService.getColumnDefs();
      this.searchService.loadGridData().subscribe (
        (data: any) => { 
          this.rowData = data;
          this.logService.logJson(this.rowData);
      });
    }

    loadFinancialproducts(): void {
      this.searchService.loadFinancialproducts().subscribe (
        (data: any) => {
          this.financialProducts_data = data;
      });
    } 

}
