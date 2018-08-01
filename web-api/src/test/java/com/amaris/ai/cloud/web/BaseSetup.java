package com.amaris.ai.cloud.web;

import com.amaris.ai.cloud.web.model.FileItem;
import com.amaris.ai.cloud.web.model.FinacialProduct;
import com.amaris.ai.cloud.web.response.SearchDocumentResponse;

public class BaseSetup {

  protected FinacialProduct buildProduct(final String fproduct) {
    final FinacialProduct fp = new FinacialProduct();
    fp.setLabel(fproduct);
    fp.setDescription(fproduct);
    fp.setFieldid(fproduct);
    return fp;
  }

  protected SearchDocumentResponse mockSearchDocument(final String product, final String document, final String content) {
    final SearchDocumentResponse result = new SearchDocumentResponse();
    result.setProjectName(product);
    result.setContent(content);
    result.setDocument(document);
    result.setParsedTbls(document);
    result.setPageNumber(-1);
    return result;
  }

  protected FileItem prepareFileItem(final String product, final Integer index) {
    final FileItem item = new FileItem();
    item.setFinancialProduct(product);
    item.setFilename(java.util.UUID.randomUUID().toString() + "-" + index + ".pdf");
    return item;
  }
}
