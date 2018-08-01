package com.amaris.ai.cloud.search.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.search.configuration.elastic.ESConfiguration;
import com.amaris.ai.cloud.search.configuration.elastic.ElasticSearchClient;
import com.amaris.ai.cloud.search.request.DocumentCountRequest;
import com.amaris.ai.cloud.search.request.SearchDocumentRequest;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;
import com.amaris.ai.cloud.search.services.ElasticSearchService;
import com.amaris.ai.cloud.search.util.QueryBuilder;
import com.amaris.ai.cloud.search.util.SearchUtil;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);

  @Autowired
  private ElasticSearchClient esClient;

  @Autowired
  private ESConfiguration esConfiguration;

  @Override
  public List<SearchDocumentResponse> listContent(final String product, final String keyword) {
    final List<SearchDocumentResponse> searchResults = new ArrayList<>();
    try {
      final BoolQueryBuilder boolBuilder = QueryBuilder.buildBoolQueryBuilder("content", keyword);
      LOGGER.info("BoolBuilder:\n" + boolBuilder.toString());
      
      final SearchSourceBuilder sourceBuilder = QueryBuilder.defaultSourceBuilder(esConfiguration.getResultSize());
      sourceBuilder.query(boolBuilder);
    
      final SearchRequest searchRequest = new SearchRequest(esConfiguration.getDocumentIndex());
      searchRequest.source(sourceBuilder);

      final SearchResponse response = esClient.searchClient().search(searchRequest);
      Arrays.asList(response.getHits().getHits()).forEach(searchhit -> {
        final String jsonData = searchhit.getSourceAsString().replaceAll("\\s+", "\\s").trim();
        final SearchDocumentResponse searchDocument = SearchUtil.readData(jsonData, SearchDocumentResponse.class);
        searchResults.add(searchDocument);
      });
    } catch (final Exception ex) {
      LOGGER.info("Error in searching elastic search with keyword:[{}] " + ex, keyword, ex);
    }
    return searchResults;
  }

  @Override
  public List<SearchDocumentResponse> listContent(final SearchDocumentRequest searchDocumentRequest) {
    return new ArrayList<>();
  }

  @Override
  public List<DocumentCountResponse> listContent(DocumentCountRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

}
