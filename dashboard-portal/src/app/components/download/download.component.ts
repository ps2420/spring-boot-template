import { Component, OnInit } from '@angular/core';

import { LogService } from './../../service/shared/log.service';
import { DocumentService } from './../../service/shared/document.service';
 
import { MenuItem } from '../../model/menu-item';

@Component({
  selector: 'app-download',
  templateUrl: './download.component.html',
  styleUrls: ['./download.component.css']
})
export class DownloadComponent implements OnInit {

    public productSearch: string = '';
    public financialProducts: any[] = []; 
    public financialProducts_data: any[] = []; 
 
    public columnDefs: any[] = [];
    public rowData: any[] = [];

    app_context: any = {};
 
    constructor(private logService: LogService, private documentService: DocumentService) { 
      this.app_context = this.documentService.getAppContext();
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

    /**
      //Service calls are starting from here..
    **/
    loadGridData() : void {
      this.columnDefs = this.documentService.getColumnDefs();
      this.loadGridDataFromServer();
    }

    loadFinancialproducts(): void {
      this.documentService.loadFinancialproducts().subscribe(
        (data: any) => {
          this.financialProducts_data = data;
      });
    } 

    loadGridDataFromServer(): void {
      this.documentService.loadFinancialProdutsAudit(this.productSearch).subscribe(
        (data: any) => {
          this.rowData = data;
      });
    }
}
