package com.amaris.ai.cloud.web.config;

import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "environment.matrix", ignoreUnknownFields = true)
public class EnvironmentMatrix implements Serializable {

  private static final long serialVersionUID = 1L;

  private String protocol;
  private String searchReference;

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getSearchReference() {
    return searchReference;
  }

  public void setSearchReference(String searchReference) {
    this.searchReference = searchReference;
  }
}
