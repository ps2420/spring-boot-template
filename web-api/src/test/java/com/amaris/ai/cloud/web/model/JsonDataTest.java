package com.amaris.ai.cloud.web.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.amaris.ai.cloud.web.BaseSetup;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
public class JsonDataTest extends BaseSetup {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonDataTest.class);

  static final ObjectMapper mapper = new ObjectMapper();

  public static final String EQUITY = "Equity";
  public static final String OPTION = "Option";
  public static final String DERIVATIVES = "Derivative";
  public static final String MOCK_DATA = "mock data, ignore it..";

  @Test
  public void downloadDocumentDataTest() throws Exception {
    final DocumentAudit dd = new DocumentAudit();
    dd.setUploadDate(Calendar.getInstance().getTime());

    final DocumentAudit _dd = new DocumentAudit();
    dd.setUploadDate(Calendar.getInstance().getTime());
    LOGGER.info("" + mapper.writeValueAsString(Arrays.asList(dd, _dd)));
  }

  @Test
  public void financialProductTest() throws Exception {
    final ObjectMapper mapper = new ObjectMapper();
    LOGGER.info("\n" + mapper.writeValueAsString(Arrays.asList(buildProduct(EQUITY), buildProduct(OPTION), buildProduct(DERIVATIVES))));
  }

  @Test
  public void searchDocumentTest() throws Exception {
    final SearchDocument sdoc1 = super.mockSearchDocument(EQUITY, java.util.UUID.randomUUID().toString() + ".pdf", MOCK_DATA);
    final SearchDocument sdoc2 = super.mockSearchDocument(OPTION, java.util.UUID.randomUUID().toString() + ".pdf", MOCK_DATA);
    final SearchDocument sdoc3 = super.mockSearchDocument(DERIVATIVES, java.util.UUID.randomUUID().toString() + ".pdf", MOCK_DATA);
    LOGGER.info("\n" + mapper.writeValueAsString(Arrays.asList(sdoc1, sdoc2, sdoc3)));
  }

  @Test
  public void fileItemJsonData() throws Exception {
    final List<FileItem> fileItemList = new ArrayList<>();
    IntStream.range(2, 20).forEach(index -> {
      if (index % 3 == 0) {
        fileItemList.add(prepareFileItem(OPTION, index));
      } else if (index % 2 == 0) {
        fileItemList.add(prepareFileItem(EQUITY, index));
      } else if (index % 5 == 0) {
        fileItemList.add(prepareFileItem(DERIVATIVES, index));
      }
    });
    LOGGER.info("\n" + mapper.writeValueAsString(fileItemList));
  }
}
