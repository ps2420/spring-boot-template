import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent }   from './components/home-page/home-page.component';
import { UploadComponent }   from './components/upload/upload.component';
import { DownloadComponent }   from './components/download/download.component';
import { SearchComponent }   from './components/search/search.component';

const routes: Routes = [
  { path: 'dashboard-portal',  component: HomePageComponent },
  { path: 'dashboard-portal',  component: HomePageComponent },
  { path: 'dashboard-portal/upload',  component: UploadComponent },
  { path: 'dashboard-portal/search',  component: SearchComponent },
  { path: 'dashboard-portal/download',  component: DownloadComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
