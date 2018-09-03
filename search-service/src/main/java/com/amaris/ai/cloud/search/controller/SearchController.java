package com.amaris.ai.cloud.search.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.search.request.DocumentCountRequest;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;
import com.amaris.ai.cloud.search.services.DocumentContentService;
import com.amaris.ai.cloud.search.services.DocumentCountService;
import com.amaris.ai.cloud.search.util.SearchUtil;

@RestController
@RequestMapping("/search/")
public class SearchController {

  @Autowired
  private DocumentContentService documentContentService;

  @Autowired
  private DocumentCountService documentCountService;

  @RequestMapping(value = "/searchDocuments", method = RequestMethod.GET)
  public List<SearchDocumentResponse> searchDocuments(final @RequestParam(name = "document", defaultValue = "") String document,
      @RequestParam(name = "keyword", defaultValue = "") final String keyword) {
    return this.documentContentService.searchDocuments(SearchUtil.prepareSearchRequest(document, keyword));
  }

  @RequestMapping(value = "/uniqueDocuments", method = RequestMethod.GET)
  public List<DocumentCountResponse> uniqueDocuments() throws Exception {
    return this.documentCountService.documentCountAcrossIndex(new DocumentCountRequest());
  }

  @PostMapping(value = "/documentCountInfo")
  public List<DocumentCountResponse> documentCountInfo(final @RequestBody DocumentCountRequest request) throws Exception {
    return this.documentCountService.documentCountAcrossIndex(request);
  }

}
