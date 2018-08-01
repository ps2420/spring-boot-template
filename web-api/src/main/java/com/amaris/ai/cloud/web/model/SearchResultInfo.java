package com.amaris.ai.cloud.web.model;

import java.io.Serializable;

public class SearchResultInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private String indexName;
  private String product;
  private Integer documentNumbers;

  public String getIndexName() {
    return indexName;
  }

  public void setIndexName(String indexName) {
    this.indexName = indexName;
  }

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public Integer getDocumentNumbers() {
    return documentNumbers;
  }

  public void setDocumentNumbers(Integer documentNumbers) {
    this.documentNumbers = documentNumbers;
  }

}
