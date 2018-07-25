package com.amaris.ai.cloud.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.web.model.SearchDocument;
import com.amaris.ai.cloud.web.service.SearchDocumentService;

@RestController
@RequestMapping("/search/")
public class SearchDocumentController {

  @Autowired
  private SearchDocumentService searchDocumentService;

  @RequestMapping(value = "/documents", method = RequestMethod.GET)
  public List<SearchDocument> listSearchDocument(@RequestParam(name = "product", defaultValue = "") final String product,
      @RequestParam(name = "keyword", defaultValue = "") final String keyword) {
    return this.searchDocumentService.listSearchDocument(product, keyword);
  }

}
