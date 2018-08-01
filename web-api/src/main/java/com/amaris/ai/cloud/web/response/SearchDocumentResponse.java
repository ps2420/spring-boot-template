package com.amaris.ai.cloud.web.response;

import java.io.Serializable;

public class SearchDocumentResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String content;
  private String document;
  private Integer pageNumber;
  private String parsedTbls;
  private String projectName;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getDocument() {
    return document;
  }

  public void setDocument(String document) {
    this.document = document;
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public String getParsedTbls() {
    return parsedTbls;
  }

  public void setParsedTbls(String parsedTbls) {
    this.parsedTbls = parsedTbls;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

}
