package com.amaris.ai.cloud.web.request;

import java.io.Serializable;

public class DocumentCountRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String product;
  private String keyword;

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
