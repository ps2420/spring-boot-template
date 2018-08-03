package com.amaris.ai.cloud.db.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.amaris.ai.cloud.db.BaseSetup;
import com.amaris.ai.cloud.db.ITTestSetup;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.util.CommonUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class DocumentAuditControllerTest extends BaseSetup {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentAuditControllerTest.class);

  @Value("${local.server.port}")
  private Integer http_port;

  final RestTemplate restTemplate = new RestTemplate();

  @Test
  public void auditDocument() throws Exception {
    final DocumentAudit docAudit = super.mockDocumentAudit();
    final String httpURL = "http://localhost:" + http_port + "/docaudit/audit";
    final String jsonData = CommonUtil.objectMapper().writeValueAsString(docAudit);
    final ResponseEntity<String> entity = restTemplate.postForEntity(httpURL, jsonData, String.class);
    final String jsonContent = entity.getBody();
    LOGGER.info("response : \n" + jsonContent);
    assertEquals(200, entity.getStatusCode().value());
    assertNotNull(entity.getBody());
    CommonUtil.logJsonData(jsonContent);
  }



}
