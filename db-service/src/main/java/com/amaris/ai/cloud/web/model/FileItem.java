package com.amaris.ai.cloud.web.model;

import java.io.Serializable;

public class FileItem implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String financialProduct;
  protected String filename;

  public String getFinancialProduct() {
    return financialProduct;
  }

  public void setFinancialProduct(String financialProduct) {
    this.financialProduct = financialProduct;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }
}
