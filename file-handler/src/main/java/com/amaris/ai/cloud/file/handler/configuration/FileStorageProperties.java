package com.amaris.ai.cloud.file.handler.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file", ignoreUnknownFields = true)
public class FileStorageProperties {

  protected String uploadDir;
  protected String downloadDir;

  public String getUploadDir() {
    return uploadDir;
  }

  public void setUploadDir(String uploadDir) {
    this.uploadDir = uploadDir;
  }

  public String getDownloadDir() {
    return downloadDir;
  }

  public void setDownloadDir(String downloadDir) {
    this.downloadDir = downloadDir;
  }

}
