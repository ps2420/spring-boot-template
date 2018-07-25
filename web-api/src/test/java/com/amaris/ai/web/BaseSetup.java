package com.amaris.ai.web;

import com.amaris.ai.web.model.FinacialProduct;
import com.amaris.ai.web.model.SearchDocument;

public class BaseSetup {

  protected FinacialProduct buildProduct(final String fproduct) {
    final FinacialProduct fp = new FinacialProduct();
    fp.setLabel(fproduct);
    fp.setDescription(fproduct);
    fp.setFieldid(fproduct);
    return fp;
  }

  protected SearchDocument mockSearchDocument(final String product,
    final String document, final String content) {
    final SearchDocument result = new SearchDocument();
    result.setProduct(product);
    result.setContent(content);
    result.setDocument(document);
    result.setContent_document(document);
    result.setPageNumber(-1);
    return result;
  }
}
