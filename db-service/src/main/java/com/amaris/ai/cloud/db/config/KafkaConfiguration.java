package com.amaris.ai.cloud.db.config;

import java.io.Serializable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.brokers", ignoreUnknownFields = true)
public class KafkaConfiguration implements Serializable {

  private static final long serialVersionUID = 1L;

  private String servers;
  private String file_topic;
  private String clientId;

  public String getServers() {
    return servers;
  }

  public void setServers(String servers) {
    this.servers = servers;
  }

  public String getFile_topic() {
    return file_topic;
  }

  public void setFile_topic(String file_topic) {
    this.file_topic = file_topic;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
}
