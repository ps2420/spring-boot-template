package com.amaris.ai.cloud.search.service.it;

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
import com.amaris.ai.cloud.search.BaseSetup;
import com.amaris.ai.cloud.search.ITTestSetup;
import com.amaris.ai.cloud.search.request.SearchDocumentRequest;
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;
import com.amaris.ai.cloud.search.services.ElasticSearchService;
import com.amaris.ai.cloud.search.util.SearchUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class ElasticSearchServiceTest extends BaseSetup {

  @Autowired
  private ElasticSearchService elasticSearchService;

  @Test
  public void listSearchDocument_whenKeyWordIsValid() throws Exception {
    final List<SearchDocumentResponse> responseList = elasticSearchService.listContent(DEBT_EQUITY_RATIO, "de");
    SearchUtil.writeJsonData(responseList);
    assertNotNull(responseList);
    assertTrue(responseList.size() > 0);
  }

  @Test
  public void listSearchDocument_whenKeyWordIsInValid() throws Exception {
    final List<SearchDocumentResponse> responseList = elasticSearchService.listContent(DEBT_EQUITY_RATIO, java.util.UUID.randomUUID().toString());
    SearchUtil.writeJsonData(responseList);
    assertNotNull(responseList);
    assertTrue(responseList.isEmpty());
  }

  @Test
  public void searchDocumentResponse_byDocumentRequest() throws Exception {
    final List<SearchDocumentResponse> responseList = elasticSearchService.listContent(new SearchDocumentRequest()); // TO-DO
    assertNotNull(responseList);
    assertTrue(responseList.isEmpty());
  }

}
