package com.amaris.ai.cloud.search.services.impl;

import static com.amaris.ai.cloud.search.util.SearchUtil.SLASH_REMOVAL;
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
import com.amaris.ai.cloud.search.configuration.ESConfiguration;
import com.amaris.ai.cloud.search.configuration.ElasticSearchClient;
import com.amaris.ai.cloud.search.request.SearchDocumentRequest;
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;
import com.amaris.ai.cloud.search.services.ElasticSearchService;
import com.amaris.ai.cloud.search.util.QueryBuilder;
import com.amaris.ai.cloud.search.util.SearchUtil;

@Service
class ElasticSearchServiceImpl implements ElasticSearchService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);

  @Autowired
  private ElasticSearchClient esClient;

  @Autowired
  private ESConfiguration esConfiguration;

  @Override
  public List<SearchDocumentResponse> searchDocuments(final SearchDocumentRequest request) {
    return searchDocuments(request.getDocument(), request.getKeyword(), request.getFrom(), request.getTo());
  }

  public List<SearchDocumentResponse> searchDocuments(final String document, final String keyword, final Integer from, final Integer to) {
    final List<SearchDocumentResponse> searchResults = new ArrayList<>();
    try {
      final BoolQueryBuilder boolBuilder = QueryBuilder.buildBoolQueryBuilder(document, keyword);
      LOGGER.info("BoolBuilder:\n" + boolBuilder.toString());

      final SearchSourceBuilder sourceBuilder = QueryBuilder.defaultSourceBuilder(from, to);
      sourceBuilder.query(boolBuilder);

      final SearchRequest searchRequest = new SearchRequest(esConfiguration.getDocumentIndex());
      searchRequest.source(sourceBuilder);

      final SearchResponse response = esClient.searchClient().search(searchRequest);
      Arrays.asList(response.getHits().getHits()).forEach(searchhit -> {
        final String jsonData = searchhit.getSourceAsString().replaceAll("\\s+", " ").trim();
        final SearchDocumentResponse searchDocument = SearchUtil.readData(jsonData, SearchDocumentResponse.class);
        searchDocument.setDocument(searchDocument.getDocument().replaceAll(SLASH_REMOVAL, ""));
        searchDocument.setParsedTbls(searchDocument.getParsedTbls().replaceAll(SLASH_REMOVAL, ""));
        searchResults.add(searchDocument);
      });
    } catch (final Exception ex) {
      LOGGER.info("Error in searching elastic search with keyword:[{}] " + ex, keyword, ex);
    }
    return searchResults;
  }

}
