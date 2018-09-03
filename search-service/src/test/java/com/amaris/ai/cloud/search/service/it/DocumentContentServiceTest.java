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
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;
import com.amaris.ai.cloud.search.services.DocumentContentService;
import com.amaris.ai.cloud.search.util.SearchUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class DocumentContentServiceTest extends BaseSetup {

  @Autowired
  private DocumentContentService documentContentService;

  @Test
  public void listSearchDocument_whenValidKeywordIsProvided() throws Exception {
    final List<SearchDocumentResponse> responseList =
        documentContentService.searchDocuments(SearchUtil.prepareSearchRequest(DOCUMENT_NAME, "Short Term Provisions"));
    SearchUtil.writeJsonData(responseList);
    assertNotNull(responseList);
    assertTrue(responseList.size() > 0);
  }

  @Test
  public void listSearchDocument_whenValidKeywordIsInValid() throws Exception {
    final List<SearchDocumentResponse> responseList =
        documentContentService.searchDocuments(SearchUtil.prepareSearchRequest(DOCUMENT_NAME, "provisions"));
    SearchUtil.writeJsonData(responseList);
    assertNotNull(responseList);
    assertTrue(responseList.size() > 0);
  }
 

}
