package com.amaris.ai.cloud.db.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.amaris.ai.cloud.db.BaseSetup;
import com.amaris.ai.cloud.db.ITTestSetup;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.util.CommonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class DBNotificationServiceTest extends BaseSetup {

  private static final Logger LOGGER = LoggerFactory.getLogger(DBNotificationServiceTest.class);

  @Autowired
  private DBNotificationService notificationService;

  @Test
  public void sendKafkaEvent() throws Exception {
    final DocumentAudit documentAudit = super.mockDocumentAudit();
    final DocumentAudit _doc = notificationService.auditDocument(documentAudit);
    assertNotNull(_doc);
    assertEquals(documentAudit.getProduct(), _doc.getProduct());
    LOGGER.info("response:\n", CommonUtil.objectMapper().writeValueAsString(_doc));
  }

}
