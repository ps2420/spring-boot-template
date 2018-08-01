package com.amaris.ai.cloud.web.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchDocumentRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String product;
  private int from;
  private int size;
  private List<SearchKeyword> keywordList = new ArrayList<>();

  public String getProduct() {
    return product;
  }

  public void setProduct(String product) {
    this.product = product;
  }

  public int getFrom() {
    return from;
  }

  public void setFrom(int from) {
    this.from = from;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public List<SearchKeyword> getKeywordList() {
    return keywordList;
  }

  public void setKeywordList(List<SearchKeyword> keywordList) {
    this.keywordList = keywordList;
  }

  public static class SearchKeyword implements Serializable {

    private static final long serialVersionUID = 1L;

    private String field;
    private String keyword;

    public String getField() {
      return field;
    }

    public void setField(String field) {
      this.field = field;
    }

    public String getKeyword() {
      return keyword;
    }

    public void setKeyword(String keyword) {
      this.keyword = keyword;
    }
  }

}
