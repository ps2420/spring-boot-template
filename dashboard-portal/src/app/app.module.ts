import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AppRoutingModule } from './app-routing.module';
import { UploadDocumentComponent } from './components/upload-document/upload-document.component';

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload'; 
 
import { FormsModule } from '@angular/forms';


import { LogService } from './service/log/log.service';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    UploadDocumentComponent
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
