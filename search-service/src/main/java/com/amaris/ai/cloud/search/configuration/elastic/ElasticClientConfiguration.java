package com.amaris.ai.cloud.search.configuration.elastic;

import java.io.Serializable;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "elastic.client", ignoreUnknownFields = true)
public class ElasticClientConfiguration implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String userid;
  protected String password;
  protected boolean authenticated;
  protected String protocol = "http";
  protected List<ElasticHostConfig> elasticHostConfigs;

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
