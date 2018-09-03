package com.amaris.ai.cloud.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.amaris.ai.cloud.db.BaseSetup;
import com.amaris.ai.cloud.db.ITTestSetup;
import com.amaris.ai.cloud.db.model.DocumentAudit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
@Transactional
public class DBAuditServiceTest extends BaseSetup {

  @Autowired
  private DBAuditService dbAuditService;

  @Test
  @Rollback
  public void insertAuditInDB() throws Exception {
    final DocumentAudit documentAudit = mockDocumentAudit();
    dbAuditService.auditDocument(documentAudit);
  }
}
