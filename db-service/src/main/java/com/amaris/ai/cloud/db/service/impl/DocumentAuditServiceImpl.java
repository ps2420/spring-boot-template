package com.amaris.ai.cloud.db.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.DBAuditService;
import com.amaris.ai.cloud.db.service.DBNotificationService;
import com.amaris.ai.cloud.db.service.DocumentAuditService;

@Service
public class DocumentAuditServiceImpl implements DocumentAuditService {

  @Autowired
  private DBNotificationService kafkaService;

  @Autowired
  private DBAuditService dbService;

  @Override
  @Transactional(rollbackFor = Exception.class, readOnly = true)
  public List<DocumentAudit> listDocumentAudits() throws Exception {
    final List<DocumentAudit> docList = new ArrayList<>();

    return docList;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public DocumentAudit auditDocument(final DocumentAudit docAudit) throws Exception {
    final DocumentAudit _docAudit = dbService.auditDocument(docAudit);
    return kafkaService.auditDocument(_docAudit);
  }

}

