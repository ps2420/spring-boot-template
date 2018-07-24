import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AppRoutingModule } from './app-routing.module'; 

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload'; 
import { FormsModule } from '@angular/forms';

import { LogService } from './service/log/log.service'; 
import { SearchComponent } from './components/search/search.component';
import { DownloadComponent } from './components/download/download.component';
import { UploadComponent } from './components/upload/upload.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent, 
    SearchComponent,
    DownloadComponent,
    UploadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    FileUploadModule
  ],
  providers: [LogService],
  bootstrap: [AppComponent]
})
export class AppModule { }
