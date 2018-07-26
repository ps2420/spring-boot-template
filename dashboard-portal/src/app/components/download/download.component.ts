import { Component, OnInit } from '@angular/core';

import { LogService } from './../../service/log/log.service';
import { DownloadDocumentService } from './../../service/document/download-document.service';

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
 
    constructor(private logService: LogService, private downloadService: DownloadDocumentService) { 
      this.productSearch = this.downloadService.getAppConfig()['app_name'];
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
    }

    /**
      //Service calls are starting from here..
    **/
    loadGridData() : void {
      this.columnDefs = this.downloadService.getColumnDefs();
      this.loadGridDataFromServer();
    }

    loadFinancialproducts(): void {
      this.downloadService.loadFinancialproducts().subscribe(
        (data: any) => {
          this.financialProducts_data = data;
      });
    } 

    loadGridDataFromServer(): void {
      this.downloadService.loadGridDataFromServer(this.productSearch).subscribe(
        (data: any) => {
          this.rowData = data;
      });
    }
}
