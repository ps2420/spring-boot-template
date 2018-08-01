package com.amaris.ai.cloud.search.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.search.model.SearchDocument;
import com.amaris.ai.cloud.search.model.SearchDocumentRequest;
import com.amaris.ai.cloud.search.services.SearchDocumentService;

@RestController
@RequestMapping("/search/")
public class SearchController {

  @Autowired
  private SearchDocumentService searchDocumentService;

  @RequestMapping(value = "/documents/{product}", method = RequestMethod.GET)
  public List<SearchDocument> listDocuments(@PathVariable String product, @RequestParam(name = "keyword", defaultValue = "") final String keyword) {
    return this.searchDocumentService.listSearchDocument(product, keyword);
  }

  @RequestMapping(value = "/document", method = RequestMethod.GET)
  public List<SearchDocument> listDocuments(final @RequestBody SearchDocumentRequest searchDocumentRequest) {
    return this.searchDocumentService.listDocuments(searchDocumentRequest);
  }

  @RequestMapping(value = "/documentList", method = RequestMethod.GET)
  public List<String> documentList() {
    return new ArrayList<>();
  }

}
