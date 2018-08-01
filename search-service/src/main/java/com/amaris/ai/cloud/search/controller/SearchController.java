package com.amaris.ai.cloud.search.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.amaris.ai.cloud.search.services.SearchDocumentService;

@RestController
@RequestMapping("/search/")
public class SearchController {

  @Autowired
  private DocumentContentService documentContentService;

  @Autowired
  private SearchDocumentService searchDocumentService;

  @RequestMapping(value = "/listContent/{product}", method = RequestMethod.GET)
  public List<SearchDocumentResponse> listContent(@PathVariable String product,
      @RequestParam(name = "keyword", defaultValue = "") final String keyword) {
    return this.documentContentService.listContent(product, keyword);
  }

  @RequestMapping(value = "/documentCount/{product}", method = RequestMethod.GET)
  public List<DocumentCountResponse> documentCount(final @PathVariable String product) throws Exception {
    return this.searchDocumentService.documentCountInfo(product);
  }

  @PostMapping(value = "/listDocument")
  public List<DocumentCountResponse> listDocument(final @RequestBody DocumentCountRequest request) throws Exception {
    return this.searchDocumentService.listDocument(request);
  }

}
