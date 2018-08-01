package com.amaris.ai.cloud.search.services.impl;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.search.request.SearchDocumentRequest;
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;
import com.amaris.ai.cloud.search.services.DocumentContentService;
import com.amaris.ai.cloud.search.services.ElasticSearchService;
import com.amaris.ai.cloud.search.util.SearchUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class DocumentContentServiceImpl implements DocumentContentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentContentService.class);

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private ElasticSearchService elasticSearchService;

  @Override
  public List<SearchDocumentResponse> listContent(final String product, final String keyword) {
    return elasticSearchService.listContent(product, keyword);
  }

  @Override
  public List<SearchDocumentResponse> mockSearchDocument(final String product, final String keyword) {
    final List<SearchDocumentResponse> searchResults = new ArrayList<>();
    final Resource resource = this.resourceLoader.getResource(SearchUtil.MOCK_CONTENT + "search-result.json");
    try {
      try (final InputStream ios = resource.getInputStream();) {
        final String jsondata = IOUtils.toString(ios, Charset.defaultCharset());
        final TypeReference<List<SearchDocumentResponse>> mapType = new TypeReference<List<SearchDocumentResponse>>() {};
        searchResults.addAll(SearchUtil.readListData(jsondata, mapType));
      }
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return searchResults;
  }

  @Override
  public List<SearchDocumentResponse> listDocuments(final SearchDocumentRequest searchDocumentRequest) {
    return elasticSearchService.listContent(searchDocumentRequest);
  }
 
}
