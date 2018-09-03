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
import com.amaris.ai.cloud.search.request.DocumentCountRequest;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.services.DocumentCountService;
import com.amaris.ai.cloud.search.util.SearchUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class DocumentCountServiceTest extends BaseSetup {

  @Autowired
  private DocumentCountService documentCountService;
  
  private final String SEARCH_KEYWORD = "provision";

  @Test
  public void countDocument_whenKeywordIsProvided() throws Exception {
    final DocumentCountRequest request = new DocumentCountRequest();
    request.setKeyword(SEARCH_KEYWORD);
    final List<DocumentCountResponse> response = documentCountService.documentCountAcrossIndex(request);
    SearchUtil.writeJsonData(response);
    assertNotNull(response);
  }

  @Test
  public void countDocument_whenDocumentNameIsProvided() throws Exception {
    final DocumentCountRequest request = new DocumentCountRequest();
    request.setDocument(DOCUMENT_NAME);
    request.setKeyword(SEARCH_KEYWORD);
    final List<DocumentCountResponse> response = documentCountService.documentCountAcrossIndex(request);
    SearchUtil.writeJsonData(response);
    assertNotNull(response);
  }

  @Test
  public void countDocument_acrossIndex() throws Exception {
    final List<DocumentCountResponse> documentList = documentCountService.documentCountAcrossIndex(new DocumentCountRequest());
    SearchUtil.writeJsonData(documentList);
    assertNotNull(documentList);
    assertTrue(documentList.size() > 0);
  }

}
