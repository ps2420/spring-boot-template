package com.amaris.ai.cloud.db.dao;

import com.amaris.ai.cloud.db.model.DocumentAudit;

public interface DBAuditDAO {

  public DocumentAudit auditDocument(final DocumentAudit documentAudit) throws Exception;
}
