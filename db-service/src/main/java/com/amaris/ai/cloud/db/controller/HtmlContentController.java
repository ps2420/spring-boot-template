package com.amaris.ai.cloud.db.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.db.model.FileItem;
import com.amaris.ai.cloud.db.model.FinacialProduct;
import com.amaris.ai.cloud.db.service.HtmlContentService;

@RestController
@RequestMapping("/html")
public class HtmlContentController {

  @Autowired
  private HtmlContentService htmlContentService;

  @RequestMapping(value = "/products", method = RequestMethod.GET)
  public List<FinacialProduct> listFinancialProducts() {
    return htmlContentService.listFinancialProducts();
  }

  @RequestMapping(value =  {"/files/{product}", "/files"}, method = RequestMethod.GET)
  public List<FileItem> listFilesByProduct(final @PathVariable Optional<String> productOpt) {
    final String product = productOpt.isPresent() ? productOpt.get() : "";
    return htmlContentService.listFilesByProduct(product);
  }
}
