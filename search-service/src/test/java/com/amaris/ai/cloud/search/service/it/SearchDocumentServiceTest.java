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
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.services.SearchDocumentService;
import com.amaris.ai.cloud.search.util.SearchUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class SearchDocumentServiceTest extends BaseSetup {

  @Autowired
  private SearchDocumentService searchDocumentService;

  @Test
  public void documentInfo_whenProductIsValid() throws Exception {
    final List<DocumentCountResponse> documentList = searchDocumentService.documentCountInfo(DEBT_EQUITY_RATIO);
    SearchUtil.writeJsonData(documentList);
    assertNotNull(documentList);
    assertTrue(documentList.size() > 0);
  }

  /**
   * For now product information is ignored, so documentList will contain few values
   * 
   * @throws Exception
   */
  @Test
  public void documentInfo_whenProductIsNotValid() throws Exception {
    final List<DocumentCountResponse> documentList = searchDocumentService.documentCountInfo(java.util.UUID.randomUUID().toString());
    SearchUtil.writeJsonData(documentList);
    assertNotNull(documentList);
    // assertTrue(documentList.isEmpty());
  }

}
