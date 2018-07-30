package com.amaris.ai.cloud.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.web.model.SearchDocument;
import com.amaris.ai.cloud.web.service.SearchESService;

@RestController
@RequestMapping("/search/")
public class SearchDocumentController {

  @Autowired
  private SearchESService searchDocumentService;

  @RequestMapping(value = "/documents/{product}", method = RequestMethod.GET)
  public List<SearchDocument> listDocuments(@PathVariable String product, @RequestParam(name = "keyword", defaultValue = "") final String keyword) {
    return this.searchDocumentService.listSearchDocument(product, keyword);
  }

}
