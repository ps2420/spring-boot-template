package com.amaris.ai.cloud.search.request;

import java.io.Serializable;

public class DocumentCountRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String document;
  private String keyword;

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
