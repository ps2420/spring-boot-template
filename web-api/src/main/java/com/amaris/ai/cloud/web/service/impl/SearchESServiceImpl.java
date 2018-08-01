package com.amaris.ai.cloud.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.amaris.ai.cloud.web.request.SearchDocumentRequest;
import com.amaris.ai.cloud.web.response.DocumentCountResponse;
import com.amaris.ai.cloud.web.response.SearchDocumentResponse;
import com.amaris.ai.cloud.web.service.SearchESService;
import com.amaris.ai.cloud.web.util.URLBuilder;
import com.amaris.ai.cloud.web.util.WebUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class SearchESServiceImpl implements SearchESService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchESService.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private URLBuilder urlBuilder;

  @Override
  public List<SearchDocumentResponse> listContent(final String product, final String keyword) {
    final List<SearchDocumentResponse> searchResults = new ArrayList<>();
    try {
      final String endpoint = urlBuilder.searchDocumentContentUrl(product, keyword);
      final String jsondata = restTemplate.exchange(endpoint, HttpMethod.GET, null, String.class).getBody();
      LOGGER.info("end-point:[{}], response : [{}]", endpoint, jsondata);
      final TypeReference<List<SearchDocumentResponse>> mapType = new TypeReference<List<SearchDocumentResponse>>() {};
      searchResults.addAll(WebUtil.readListData(jsondata, mapType));
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return searchResults;
  }

  @Override
  public List<DocumentCountResponse> documentCountInfo(String product) throws Exception {
    final List<DocumentCountResponse> searchResults = new ArrayList<>();
    try {
      final String endpoint = urlBuilder.searchDocumentCountUrl(product);
      final String jsondata = restTemplate.exchange(endpoint, HttpMethod.GET, null, String.class).getBody();
      LOGGER.info("end-point:[{}], response : [{}]", endpoint, jsondata);
      final TypeReference<List<DocumentCountResponse>> mapType = new TypeReference<List<DocumentCountResponse>>() {};
      searchResults.addAll(WebUtil.readListData(jsondata, mapType));
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return searchResults;
  }


  @Override
  public List<SearchDocumentResponse> listDocuments(final SearchDocumentRequest searchDocumentRequest) {
    
    return null;
  }

}
