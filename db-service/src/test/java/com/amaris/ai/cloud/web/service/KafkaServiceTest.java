package com.amaris.ai.cloud.web.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.DocumentAuditService;
import com.amaris.ai.cloud.db.service.KafkaService;
import com.amaris.ai.cloud.web.BaseSetup;
import com.amaris.ai.cloud.web.ITTestSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class KafkaServiceTest extends BaseSetup {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaServiceTest.class);

  @Autowired
  private KafkaService kafkaService;

  @Autowired
  private DocumentAuditService documentAuditService;

  @Test
  public void sendDocumentAuditEvent() {
    try {
      final List<DocumentAudit> docList = documentAuditService.listDocumentAudits();
      assertNotNull(docList);
      assertTrue(docList.size() > 0);

      final DocumentAudit docAudit = docList.get(0);
      docAudit.setId(java.util.UUID.randomUUID().toString());
      kafkaService.sendDocumentAuditEvent(docAudit);
    } catch (final Exception ex) {
      LOGGER.error("Error in sending document audit event to kafka topic " + ex, ex);
      assertTrue(false);
    }
  }

}
