package com.amaris.ai.cloud.search.model;

import java.io.Serializable;

public class SearchDocument implements Serializable {

  private static final long serialVersionUID = 1L;

  private String product;
  private String document;
  private String content;
  private String content_document;
  private Integer pageNumber;
  private Integer documentNumber;

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public String getContent_document() {
    return content_document;
  }

  public void setContent_document(String content_document) {
    this.content_document = content_document;
  }

  public Integer getDocumentNumber() {
    return documentNumber;
  }

  public void setDocumentNumber(Integer documentNumber) {
    this.documentNumber = documentNumber;
  }

}
