package com.amaris.ai.cloud.search.configuration.elastic;

import java.io.Serializable;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "elastic.client", ignoreUnknownFields = true)
public class ESConfiguration implements Serializable {

  private static final long serialVersionUID = 1L;

  private String userid;
  private String password;
  private boolean authenticated;
  private String documentIndex;
  private Integer resultSize = 50;
  private String protocol = "http";
  private List<ElasticHostConfig> elasticHostConfigs;

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isAuthenticated() {
    return authenticated;
  }

  public void setAuthenticated(boolean authenticated) {
    this.authenticated = authenticated;
  }

  public String getDocumentIndex() {
    return documentIndex;
  }

  public void setDocumentIndex(String documentIndex) {
    this.documentIndex = documentIndex;
  }

  public Integer getResultSize() {
    return resultSize;
  }

  public void setResultSize(Integer resultSize) {
    this.resultSize = resultSize;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public List<ElasticHostConfig> getElasticHostConfigs() {
    return elasticHostConfigs;
  }

  public void setElasticHostConfigs(List<ElasticHostConfig> elasticHostConfigs) {
    this.elasticHostConfigs = elasticHostConfigs;
  }

  public static class ElasticHostConfig {

    protected String host;
    protected Integer port;

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public Integer getPort() {
      return port;
    }

    public void setPort(Integer port) {
      this.port = port;
    }
  }

}
