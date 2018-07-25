package com.amaris.ai.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.web.model.FinacialProduct;
import com.amaris.ai.web.service.HtmlContentService;

@RestController
@RequestMapping("/html/")
public class HtmlContentController {

  @Autowired
  private HtmlContentService htmlContentService;

  @RequestMapping(value = "/financeProducts", method = RequestMethod.GET)
  public List<FinacialProduct> listFinancialProducts() {
    return htmlContentService.listFinancialProducts();
  }
}
