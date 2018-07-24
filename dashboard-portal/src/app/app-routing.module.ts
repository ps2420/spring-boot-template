import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent }   from './components/home-page/home-page.component';
import { UploadComponent }   from './components/upload/upload.component';
import { DownloadComponent }   from './components/download/download.component';
import { SearchComponent }   from './components/search/search.component';

const routes: Routes = [
  { path: '',  component: HomePageComponent },
  { path: 'upload',  component: UploadComponent },
  { path: 'search',  component: SearchComponent },
  { path: 'download',  component: DownloadComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
