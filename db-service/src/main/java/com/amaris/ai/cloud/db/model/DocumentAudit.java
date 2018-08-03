package com.amaris.ai.cloud.db.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DocumentAudit implements Serializable {

  private static final long serialVersionUID = 1L;

  private String id;
  private String product;
  private String document;
  private String uploadedBy;
  private String comments;
  private Date uploadDate = Calendar.getInstance().getTime();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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
