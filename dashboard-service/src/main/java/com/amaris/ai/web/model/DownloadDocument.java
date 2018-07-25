package com.amaris.ai.web.model;

import java.io.Serializable;
import java.util.Date;

public class DownloadDocument implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String domain;
  protected String document;
  protected String uploadedBy;
  protected Date uploadDate;

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
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

  public Date getUploadDate() {
    return uploadDate;
  }

  public void setUploadDate(Date uploadDate) {
    this.uploadDate = uploadDate;
  }


}
