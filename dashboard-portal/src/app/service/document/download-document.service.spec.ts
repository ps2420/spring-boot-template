import { TestBed, inject } from '@angular/core/testing';

import { DownloadDocumentService } from './download-document.service';

describe('DownloadDocumentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DownloadDocumentService]
    });
  });

  it('should be created', inject([DownloadDocumentService], (service: DownloadDocumentService) => {
    expect(service).toBeTruthy();
  }));
});
