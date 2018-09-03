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

    public documentSearch: string = "";
    public keywordSearch: string;
   
    public app_context: any = {};

    public gridOptions: GridOptions;
    //private gridApi: any;
    //private columnApi: any;
 
    constructor(private logService: LogService, private searchService: SearchService,
      private colDefBuilder: ColDefBuilderService) { 
      this.app_context = this.searchService.getAppContext();
      this.documentSearch = this.app_context['app_config']['app_name'];
 
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
      this.loadGridData();
      //this.gridOptions.api = this.gridApi;
      //this.gridOptions.columnApi = this.columnApi;
    }

    public financialProductSelected(item: MenuItem): void {
      this.documentSearch = item.label;
    }
 
    public keywordSearchChange(): void {
      this.logService.log("keyword search: " + this.keywordSearch);
      this.loadGridData();
    }
 
    changeTypeaheadLoading(e: boolean): void { 
    
    }
   
    fileNameOnSelect(data: TypeaheadMatch): void {
      this.fileNameSelected = data.item.name;
      this.loadGridData();
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
      if(this.keywordSearch && this.keywordSearch !== '') {
        this.searchService.loadGridData(this.fileNameSelected, this.keywordSearch)
        .do((response: Response) => {
           //this.logService.logJsonMsg("inside loadGridData method..", response);
        })
        .subscribe (
          (data: any) => {
            this.gridOptions.api.setRowData(data);
        });
      }
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






