package com.amaris.ai.cloud.db.service.impl;

import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.DBAuditService;

@Service
public class DBAuditServiceImpl implements DBAuditService {

  @Override
  public DocumentAudit auditDocument(final DocumentAudit documentAudit) throws Exception {
    documentAudit.setId(java.util.UUID.randomUUID().toString()); // id should be generated from spring-jpa-hibernate
    // TO-DO use JPA to save this document

    return documentAudit;
  }

}
