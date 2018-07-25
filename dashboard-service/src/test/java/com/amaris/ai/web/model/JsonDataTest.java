package com.amaris.ai.web.model;

import java.util.Arrays;
import java.util.Calendar;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
public class JsonDataTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(JsonDataTest.class);

  @Test
  public void downloadDocumentDataTest() throws Exception {
    final DownloadDocument dd = new DownloadDocument();
    dd.setUploadDate(Calendar.getInstance().getTime());

    final DownloadDocument _dd = new DownloadDocument();
    dd.setUploadDate(Calendar.getInstance().getTime());

    final ObjectMapper mapper = new ObjectMapper();
    LOGGER.info("" + mapper.writeValueAsString(Arrays.asList(dd, _dd)));
  }

}
