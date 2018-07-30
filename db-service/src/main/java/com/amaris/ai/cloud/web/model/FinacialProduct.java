package com.amaris.ai.cloud.web.model;

import java.io.Serializable;

public class FinacialProduct implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String label;
  protected String description;
  protected boolean selected;
  protected String fieldid;

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
  
  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public String getFieldid() {
    return fieldid;
  }

  public void setFieldid(String fieldid) {
    this.fieldid = fieldid;
  }
}
