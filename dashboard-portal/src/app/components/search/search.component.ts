import { Component, OnInit } from '@angular/core';

import { LogService } from './../../service/shared/log.service';
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

    app_context: any = {};
 
    constructor(private logService: LogService, private searchService: SearchService) { 
      this.app_context = this.searchService.getAppContext();
      this.productSearch = this.app_context['app_config']['app_name'];
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
      if(this.productSearch === '') {
        this.financialProducts = this.financialProducts_data;
      } else {
        this.financialProducts = this.financialProducts_data.filter(item => {
          return item.label.toLowerCase().indexOf(this.productSearch.toLowerCase()) >= 0; 
        });
      }
      return this.financialProducts;
    }

    loadGridData() : void {
      this.columnDefs = this.searchService.getColumnDefs();
      this.searchService.loadGridData(this.productSearch, this.keywordSearch).subscribe (
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
