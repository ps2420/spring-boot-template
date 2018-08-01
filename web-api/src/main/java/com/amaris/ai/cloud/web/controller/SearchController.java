package com.amaris.ai.cloud.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.web.response.DocumentCountResponse;
import com.amaris.ai.cloud.web.response.SearchDocumentResponse;
import com.amaris.ai.cloud.web.service.SearchESService;

@RestController
@RequestMapping("/search/")
public class SearchController {

  @Autowired
  private SearchESService searchESService;

  @RequestMapping(value = "/listContent/{product}", method = RequestMethod.GET)
  public List<SearchDocumentResponse> listContent(@PathVariable String product,
      @RequestParam(name = "keyword", defaultValue = "") final String keyword) {
    return this.searchESService.listContent(product, keyword);
  }

  @RequestMapping(value = "/documentCount/{product}", method = RequestMethod.GET)
  public List<DocumentCountResponse> documentCountInfo(final @PathVariable String product) throws Exception {
    return this.searchESService.documentCountInfo(product);
  }

}
