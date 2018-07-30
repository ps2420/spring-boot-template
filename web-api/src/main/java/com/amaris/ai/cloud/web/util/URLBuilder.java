package com.amaris.ai.cloud.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amaris.ai.cloud.web.config.EnvironmentMatrix;

@Component
public class URLBuilder {

  @Autowired
  private EnvironmentMatrix envMatrix;


  private String urlPrefix() {
    return envMatrix.getProtocol() + "://";
  }

  private String esUrlPrefix() {
    return urlPrefix() + envMatrix.getSearchReference();
  }

  private String dbUrlPrefix() {
    return urlPrefix() + envMatrix.getDbReference();
  }

  public String searchDocumentUrl(final String product, final String keyword) {
    return esUrlPrefix() + "/search/documents" + "/" + product + "/?keyword=" + keyword;
  }

  public String docListAuditUril(final String product) {
    return dbUrlPrefix() + "/docaudit/list/" + product;
  }

}
