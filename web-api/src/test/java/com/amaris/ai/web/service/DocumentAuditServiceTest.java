package com.amaris.ai.web.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.amaris.ai.web.ITTestSetup;
import com.amaris.ai.web.model.DocumentAudit;
import com.amaris.ai.web.util.WebUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class DocumentAuditServiceTest {

  @Autowired
  private DocumentAuditService documentAuditService;

  @Test
  public void testDocumentAuditData() throws Exception {
    final List<DocumentAudit> docList = documentAuditService.listDocumentAudits(null);
    WebUtil.objectMapper().writeValueAsString(docList);
    assertNotNull(docList);
    assertTrue(docList.size() > 0);
  }
}
