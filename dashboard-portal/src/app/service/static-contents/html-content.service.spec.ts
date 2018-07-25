import { TestBed, inject } from '@angular/core/testing';

import { HtmlContentService } from './html-content.service';

describe('HtmlContentService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [HtmlContentService]
    });
  });

  it('should be created', inject([HtmlContentService], (service: HtmlContentService) => {
    expect(service).toBeTruthy();
  }));
});
