package com.amaris.ai.cloud.file.handler.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.amaris.ai.cloud.file.handler.model.DocumentAudit;
import com.amaris.ai.cloud.file.handler.util.FileHandlerCommonUtil;
import com.amaris.ai.cloud.file.handler.util.URLBuilder;

@Service
public class DocumentAuditService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentAuditService.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private URLBuilder urlBuilder;

  public DocumentAudit auditDocument(final String document) throws Exception {
    final String endpoint = urlBuilder.documentAuditUrl();
    final DocumentAudit docAudit = documentAudit(document);
    LOGGER.info("Making http call for doc audit :[{}]", endpoint); 
    final ResponseEntity<String> response = restTemplate.postForEntity(endpoint, docAudit, String.class);
    LOGGER.info("response-status-code:[{}], body:[{}]", response.getStatusCode().value(), response.getBody());
    return FileHandlerCommonUtil.objectMapper().readValue(response.getBody(), DocumentAudit.class);
  }

  private DocumentAudit documentAudit(final String document) {
    final DocumentAudit docAudit = new DocumentAudit();
    docAudit.setDocument(document);
    return docAudit;
  }

}
