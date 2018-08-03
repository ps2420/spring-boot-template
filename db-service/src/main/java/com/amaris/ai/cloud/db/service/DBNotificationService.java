package com.amaris.ai.cloud.db.service;

import com.amaris.ai.cloud.db.model.DocumentAudit;

public interface DBNotificationService {

  public DocumentAudit auditDocument(final DocumentAudit documentAudit) throws Exception;
}
