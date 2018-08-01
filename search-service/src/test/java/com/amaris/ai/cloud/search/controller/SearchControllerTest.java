package com.amaris.ai.cloud.search.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
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
import com.amaris.ai.cloud.search.BaseSetup;
import com.amaris.ai.cloud.search.ITTestSetup;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.util.SearchUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class SearchControllerTest extends BaseSetup {

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchControllerTest.class);

  @Value("${local.server.port}")
  private Integer http_port;

  final RestTemplate restTemplate = new RestTemplate();

  @Test
  public void searchContentsByProductAndKeyWord() throws Exception {
    LOGGER.info("initialized http_port : " + http_port);
    final String httpURL = "http://localhost:" + http_port + "/search/listContent/eq?keyword=de";
    final ResponseEntity<String> response = restTemplate.getForEntity(httpURL, String.class);
    LOGGER.info("response-content:\n" + response.getBody());
    assertEquals(response.getStatusCode().value(), 200);
    assertTrue(response.getBody().length() > 5);
  }

  @Test
  public void searchDistinctDocuments_byProduct() throws Exception {
    LOGGER.info("initialized http_port : " + http_port);
    final String httpURL = "http://localhost:" + http_port + "/search/documentCount/eq";
    final ResponseEntity<String> response = restTemplate.getForEntity(httpURL, String.class);
    LOGGER.info("response-content:\n" + response.getBody());
    assertEquals(response.getStatusCode().value(), 200);
 
    final TypeReference<List<DocumentCountResponse>> mapType = new TypeReference<List<DocumentCountResponse>>() {};
    final List<DocumentCountResponse> resultList = SearchUtil.readListData(response.getBody().trim(), mapType);
    SearchUtil.writeJsonData(resultList);
  }

}
