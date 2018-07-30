package com.amaris.ai.cloud.db.model;

import java.io.Serializable;
import java.util.Date;

public class DocumentAudit implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String product;
  protected String document;
  protected String uploadedBy;
  protected String comments;
  protected Date uploadDate;

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

  public String getUploadedBy() {
    return uploadedBy;
  }

  public void setUploadedBy(String uploadedBy) {
    this.uploadedBy = uploadedBy;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getComments() {
    return comments;
  }

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }


}
