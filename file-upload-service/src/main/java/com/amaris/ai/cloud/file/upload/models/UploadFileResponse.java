package com.amaris.ai.cloud.file.upload.models;

import java.io.Serializable;

public class UploadFileResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  protected String fileName;
  protected String fileDownloadUri;
  protected String fileType;
  protected long size;

  public UploadFileResponse(final String fileName, final String fileDownloadUri, final String fileType, final long size) {
    this.fileName = fileName;
    this.fileDownloadUri = fileDownloadUri;
    this.fileType = fileType;
    this.size = size;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getFileName() {
    return fileName;
  }

  public String getFileDownloadUri() {
    return fileDownloadUri;
  }

  public String getFileType() {
    return fileType;
  }

  public long getSize() {
    return size;
  }

}
