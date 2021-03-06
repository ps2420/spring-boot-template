import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AppRoutingModule } from './app-routing.module'; 

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload'; 
import { FormsModule } from '@angular/forms';
import { AgGridModule } from 'ag-grid-angular';

import { HttpClientModule } from '@angular/common/http';
 
import { SearchComponent } from './components/search/search.component';
import { DownloadComponent } from './components/download/download.component';
import { UploadComponent } from './components/upload/upload.component';

import { TypeaheadModule } from 'ngx-bootstrap/typeahead';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { HighlightPipe } from './pipe/highlight.pipe';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent, 
    SearchComponent,
    DownloadComponent,
    UploadComponent,
    HighlightPipe
  ],
  imports: [
    BrowserModule,
    AgGridModule.withComponents([]),
    AppRoutingModule,
    FormsModule,
    FileUploadModule,
    HttpClientModule,
    TypeaheadModule.forRoot(),
    TooltipModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
