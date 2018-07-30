package com.amaris.ai.cloud.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.amaris.ai.cloud.web.config.EnvironmentMatrix;
import com.amaris.ai.cloud.web.model.SearchDocument;
import com.amaris.ai.cloud.web.service.HtmlContentService;
import com.amaris.ai.cloud.web.service.SearchESService;
import com.amaris.ai.cloud.web.util.WebUtil;

@Service
public class SearchESServiceImpl implements SearchESService {

  private static final Logger LOGGER = LoggerFactory.getLogger(HtmlContentService.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private EnvironmentMatrix envMatrix;

  @Override
  public List<SearchDocument> listSearchDocument(final String product, final String keyword) {
    final List<SearchDocument> searchResults = new ArrayList<>();
    try {
      final String endpoint = WebUtil.urlPrefix(envMatrix) + "/search/documents/" + product + "/?keyword=" + keyword;
      final String jsondata = restTemplate.exchange(endpoint, HttpMethod.GET, null, String.class).getBody();
      LOGGER.info("end-point:[{}], response : [{}]", endpoint, jsondata);
      searchResults.addAll(WebUtil.readListData(jsondata, SearchDocument.class));
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return searchResults;
  }

}
