import { Component, OnInit } from '@angular/core';
import { GridOptions} from "ag-grid";

import { LogService } from './../../service/shared/log.service';
import { ColDefBuilderService } from './../../service/shared/col-def-builder.service';
import { SearchService } from './../../service/search/search.service';

import { MenuItem } from '../../model/menu-item';

import { TypeaheadMatch } from 'ngx-bootstrap/typeahead';

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
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

    fileNameSelected: string = "";
    fileDataSource: Observable<any>;

    public productSearch: string;
    public keywordSearch: string;

    public financialProducts: any[] = []; 
    public financialProducts_data: any[] = []; 
    
    private app_context: any = {};

    private gridOptions: GridOptions;
    //private gridApi: any;
    //private columnApi: any;
 
    constructor(private logService: LogService, private searchService: SearchService,
      private colDefBuilder: ColDefBuilderService) { 
      this.app_context = this.searchService.getAppContext();
      this.productSearch = this.app_context['app_config']['app_name'];
 
      this.initGrid(); 

      this.fileDataSource = Observable.create((observer: any) => {
        observer.next(this.fileNameSelected);
      }).pipe(mergeMap((searchKeyword: string) =>  {  
          return this.loadFileDataSource(searchKeyword);
        })
      );
    }

    onGridReady(params: any): void {
      //this.gridApi = params.api;
      //this.columnApi = params.columnApi; 
    }

    ngOnInit() {
      this.loadFinancialproducts();
      this.loadGridData();
      //this.gridOptions.api = this.gridApi;
      //this.gridOptions.columnApi = this.columnApi;
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

    public keywordSearchChange(): void {
      this.logService.log("keyword search: " + this.keywordSearch);
      this.loadGridData();
    }
 
    changeTypeaheadLoading(e: boolean): void { 
    
    }
   
    typeaheadOnSelect(data: TypeaheadMatch): void {
      this.fileNameSelected = data.item.name;
    }

    initGrid(): void {
      this.gridOptions = <GridOptions> {
        rowData  : [],
        columnDefs: this.colDefBuilder.buildSearchGridColDef(),
        enableColResize: true,
        onGridReady : this.onGridReady,
        enableSorting: true
      };
    }

    loadFileDataSource(searchKeyword: string): Observable<any> {
      const query = new RegExp(searchKeyword, 'ig');
      return  this.searchService.listFilesByProduct(this.fileNameSelected)
        .do((response: Response) => {
          this.logService.logJson(response);
        })
        .map((response: Response) =>  <any>response)
        .catch(error => of("Error in retrieving records.."));
    }

    loadGridData() : void {
      let product_id = this.app_context['app_config']['product_id'];
      this.searchService.loadGridData(product_id, this.keywordSearch)
      .do((response: Response) => {
         //this.logService.logJsonMsg("inside loadGridData method..", response);
      })
      .subscribe (
        (data: any) => {
          this.gridOptions.api.setRowData(data);
      });
    }

    loadFinancialproducts(): void {
      this.searchService.loadFinancialproducts().subscribe (
        (data: any) => {
          this.financialProducts_data = data;
      });
    } 

    onColumnSelection(object: any, params: any) : void {
      if(params['colDef'].field == "document") {
        this.searchService.downloadFile(params.data.document)
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






