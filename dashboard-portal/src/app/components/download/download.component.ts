import { Component, OnInit } from '@angular/core';

import { LogService } from './../../service/shared/log.service';
import { ColDefBuilderService } from './../../service/shared/col-def-builder.service';
import { DocumentService } from './../../service/shared/document.service';
 
import { MenuItem } from '../../model/menu-item';

import { TypeaheadMatch } from 'ngx-bootstrap/typeahead';
import { GridOptions} from "ag-grid";

import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';

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

    gridOptions: GridOptions;
    
    constructor(private logService: LogService, private documentService: DocumentService,
      private colDefBuilder: ColDefBuilderService) { 
      this.app_context = this.documentService.getAppContext();
      this.productSearch = this.app_context['app_config']['app_name'];

      this.initializeDownloadGrid();
    }

    ngOnInit() {
      this.loadFinancialproducts();
      this.loadGridDataFromServer();
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
 
    initializeDownloadGrid(): void {
      this.gridOptions = <GridOptions> {
          rowData  : [],
          columnDefs: this.colDefBuilder.buildDownloadGridColDef(),
          enableColResize: true,
          //onGridReady : this.onGridReady,
          enableSorting : true,
          enableFilter  : true
      };
    }

    loadFinancialproducts(): void {
      this.documentService.loadFinancialproducts()
        .subscribe(
           (data: any) => (this.financialProducts_data = data)
        );
    } 

    loadGridDataFromServer(): void {
      this.documentService.loadFinancialProdutsAudit(this.productSearch)
        .subscribe(
            (data: any) =>   {
              this.gridOptions.api.setRowData(data);
            }
        );
    }

    onColumnSelection(object: any, params: any) : void {
      if(params['colDef'].field == "document") {
        this.documentService.downloadFile(params.data.document)
          //.map((response: Response) => response )
          .subscribe(response => {
            let b:any = new Blob([response], { type: response.type });
            var url= window.URL.createObjectURL(b);
            window.open(url);
          }
        );
      }
    }
}
