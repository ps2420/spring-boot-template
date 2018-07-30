import { Component, OnInit } from '@angular/core';
import { GridOptions} from "ag-grid";

import { LogService } from './../../service/shared/log.service';
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
    dataSource: Observable<any>;
 
    getStatesAsObservable(token: string): Observable<any> {
      const query = new RegExp(token, 'ig');
      return  this.searchService.listFilesByProduct(this.fileNameSelected)
        .map((response: Response) =>  <any>response).do(data => this.logService.logJson(data))
        .catch(error => of("Error in retrieving records.."));
    }
   
    changeTypeaheadLoading(e: boolean): void { 
    }
   
    typeaheadOnSelect(data: TypeaheadMatch): void {
      this.fileNameSelected = data.item.filename;
    }

    public productSearch: string;
    public keywordSearch: string;

    public financialProducts: any[] = []; 
    public financialProducts_data: any[] = []; 
    
    private app_context: any = {};

    private gridOptions: GridOptions;
    //private gridApi: any;
    //private columnApi: any;
 
    constructor(private logService: LogService, private searchService: SearchService) { 
      this.app_context = this.searchService.getAppContext();
      this.productSearch = this.app_context['app_config']['app_name'];
 
      this.gridOptions = <GridOptions> {
          rowData  : [],
          columnDefs: this.searchService.getSearchDocumentGridColumDefs(),
          enableColResize: true,
          onGridReady : this.onGridReady,
          enableSorting: true
      };

      this.dataSource = Observable.create((observer: any) => {
        observer.next(this.fileNameSelected);
      })
      .pipe(
        mergeMap((token: string) =>  { 
          console.log("token : " + token);
          return this.getStatesAsObservable(token);

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
    }
 

    loadGridData() : void {
      this.searchService.loadGridData(this.productSearch, this.keywordSearch).subscribe (
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
          this.downloadFile(params.data);
      }
    }

    downloadFile(data: any): void {
      this.searchService.downloadFile(data.document);    
    }
}






