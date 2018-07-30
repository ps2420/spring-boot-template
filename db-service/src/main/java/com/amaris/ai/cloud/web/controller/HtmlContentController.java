package com.amaris.ai.cloud.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.web.model.FileItem;
import com.amaris.ai.cloud.web.model.FinacialProduct;
import com.amaris.ai.cloud.web.service.HtmlContentService;

@RestController
@RequestMapping("/html/")
public class HtmlContentController {

  @Autowired
  private HtmlContentService htmlContentService;

  @RequestMapping(value = "/financeProducts", method = RequestMethod.GET)
  public List<FinacialProduct> listFinancialProducts() {
    return htmlContentService.listFinancialProducts();
  }
  
  @RequestMapping(value = "/files", method = RequestMethod.GET)
  public List<FileItem> listFilesByProduct(@RequestParam(name = "product", defaultValue = "") final String product) {
    return htmlContentService.listFilesByProduct(product);
  }
}
