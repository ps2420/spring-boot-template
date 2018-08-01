package com.amaris.ai.cloud.db.service;

import com.amaris.ai.cloud.db.model.DocumentAudit;

public interface KafkaService {

  public void sendDocumentAuditEvent(final DocumentAudit documentAudit) throws Exception;
}
