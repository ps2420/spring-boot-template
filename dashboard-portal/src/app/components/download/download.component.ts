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

    public domainSearch: string;
    public domainList: MenuItem[] = []; 
 
    public columnDefs: any[] = [];
    public rowData: any[] = [];
 
    constructor(private logService: LogService, private downloadService: DownloadDocumentService) { 
      
    }

    ngOnInit() {
       this.loadGridData();
    }


    loadGridData() : void {
       this.columnDefs = this.downloadService.getColumnDefs();  
       this.rowData = this.downloadService.loadGridData();
       this.logService.logJson(this.rowData);
    }

    public domainSearchChange(): void {
       this.domainList = this.downloadService.loadDomainValues(this.domainSearch);
    } 

    public domainSearchSelected(item: MenuItem): void {
       this.domainSearch = item.label;
       this.domainList = [];
    }

}
