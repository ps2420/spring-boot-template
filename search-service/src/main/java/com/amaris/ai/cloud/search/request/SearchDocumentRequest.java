package com.amaris.ai.cloud.search.request;

import java.io.Serializable;

public class SearchDocumentRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String document;
  private int from = 0;
  private int to = 200;
  private String keyword;

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getTo() {
    return to;
  }

  public void setTo(int to) {
    this.to = to;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
