package com.amaris.ai.cloud.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amaris.ai.cloud.db.dao.DBAuditDAO;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.DBAuditService;

@Service
public class DBAuditServiceImpl implements DBAuditService {

  @Autowired
  private DBAuditDAO dbAuditDAO;
  
  @Override
  @Transactional(rollbackFor = Exception.class)
  public DocumentAudit auditDocument(final DocumentAudit documentAudit) throws Exception {
    return dbAuditDAO.auditDocument(documentAudit);
  }

}
