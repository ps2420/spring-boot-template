package com.amaris.ai.cloud.search.services.impl;

import java.util.List;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.search.configuration.elastic.ESConfiguration;
import com.amaris.ai.cloud.search.configuration.elastic.ElasticSearchClient;
import com.amaris.ai.cloud.search.request.DocumentCountRequest;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.services.SearchDocumentService;
import com.amaris.ai.cloud.search.util.QueryBuilder;
import com.amaris.ai.cloud.search.util.SearchUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class SearchDocumentServiceImpl implements SearchDocumentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchDocumentService.class);

  @Autowired
  private ElasticSearchClient esClient;

  @Autowired
  private ESConfiguration esConfiguration;

  @Override
  public List<DocumentCountResponse> documentCountInfo(final String product) throws Exception {
    final SearchSourceBuilder searchSourceBuilder = QueryBuilder.defaultSourceBuilder(0);

    final TermsAggregationBuilder aggregation = AggregationBuilders.terms("unique_document").field("document");
    searchSourceBuilder.aggregation(aggregation);

    final SearchRequest searchRequest = new SearchRequest(esConfiguration.getDocumentIndex());
    searchRequest.types(SearchUtil.DEFAULT_INDEX_TYPE);
    searchRequest.source(searchSourceBuilder);

    final SearchResponse response = esClient.searchClient().search(searchRequest);
    LOGGER.info("\n" + response.toString().replaceAll("\\s+", " ").trim());
    final JsonNode fullDocument = SearchUtil.objectMapper().readTree(response.toString().replaceAll("\\s+", " ").trim().getBytes());

    final String jsondata = SearchUtil.parseAggregateDocuments(fullDocument);
    return SearchUtil.readListData(jsondata, new TypeReference<List<DocumentCountResponse>>() {});
  }

  @Override
  public List<DocumentCountResponse> listDocument(final DocumentCountRequest request) throws Exception {
    // TO-DO need to add groupby clause
    return documentCountInfo(request.getProduct());
  }

}
