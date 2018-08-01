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
import com.amaris.ai.cloud.search.model.SearchDocument;
import com.amaris.ai.cloud.search.model.SearchDocumentRequest;
import com.amaris.ai.cloud.search.services.ElasticSearchService;
import com.amaris.ai.cloud.search.services.SearchDocumentService;
import com.amaris.ai.cloud.search.util.SearchUtil;

@Service
public class SearchDocumentServiceImpl implements SearchDocumentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchDocumentService.class);

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private ElasticSearchService elasticSearchService;

  @Override
  public List<SearchDocument> listSearchDocument(final String product, final String keyword) {
    return elasticSearchService.listSearchDocument(product, keyword);
  }

  @Override
  public List<SearchDocument> mockSearchDocument(final String product, final String keyword) {
    final List<SearchDocument> searchResults = new ArrayList<>();
    final Resource resource = this.resourceLoader.getResource(SearchUtil.MOCK_CONTENT + "search-result.json");
    try {
      try (final InputStream ios = resource.getInputStream();) {
        final String jsondata = IOUtils.toString(ios, Charset.defaultCharset());
        searchResults.addAll(SearchUtil.readListData(jsondata, SearchDocument.class));
      }
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return searchResults;
  }

  @Override
  public List<SearchDocument> listDocuments(final SearchDocumentRequest searchDocumentRequest) {
    return elasticSearchService.listSearchDocument(searchDocumentRequest);
  }


}
