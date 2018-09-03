package com.amaris.ai.cloud.search.services.impl;

import static com.amaris.ai.cloud.search.util.SearchUtil.DOCUMENT;
import static com.amaris.ai.cloud.search.util.SearchUtil.SLASH_REMOVAL;
import static org.springframework.util.StringUtils.isEmpty;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import com.amaris.ai.cloud.search.configuration.ESConfiguration;
import com.amaris.ai.cloud.search.configuration.ElasticSearchClient;
import com.amaris.ai.cloud.search.request.DocumentCountRequest;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.response.SearchCountResponse;
import com.amaris.ai.cloud.search.services.DocumentCountService;
import com.amaris.ai.cloud.search.util.QueryBuilder;
import com.amaris.ai.cloud.search.util.SearchUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class DocumentCountServiceImpl implements DocumentCountService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentCountService.class);

  private RestTemplate restTemplate = new RestTemplate();

  @Autowired
  private ESConfiguration esConfiguration;

  @Autowired
  private ElasticSearchClient esClient;

  @Override
  public List<DocumentCountResponse> documentCountAcrossIndex(final DocumentCountRequest request) throws Exception {
    final SearchSourceBuilder sourceBuilder = QueryBuilder.defaultSourceBuilder(0, 0);

    final TermsAggregationBuilder aggregation = AggregationBuilders.terms("unique_document").field(DOCUMENT);
    sourceBuilder.aggregation(aggregation);

    if (!StringUtils.isEmpty(request.getKeyword())) {
      final BoolQueryBuilder boolBuilder = QueryBuilder.buildBoolQueryBuilder(request.getDocument(), request.getKeyword());
      sourceBuilder.query(boolBuilder);
    }

    final SearchRequest searchRequest = new SearchRequest(esConfiguration.getDocumentIndex());
    searchRequest.types(SearchUtil.DEFAULT_INDEX_TYPE);
    searchRequest.source(sourceBuilder);
    LOGGER.debug("search-request" + searchRequest.toString());

    final SearchResponse response = esClient.searchClient().search(searchRequest);
    LOGGER.info("\n" + response.toString().replaceAll("\\s+", " ").trim());
    final JsonNode fullDocument = SearchUtil.objectMapper().readTree(response.toString().replaceAll("\\s+", " ").trim().getBytes());

    final String jsondata = SearchUtil.parseAggregateDocuments(fullDocument);
    final List<DocumentCountResponse> docList = SearchUtil.readListData(jsondata, new TypeReference<List<DocumentCountResponse>>() {});
    docList.parallelStream().filter(document -> !isEmpty(document.getName())).forEach(document -> {
      document.setName(document.getName().replaceAll(SLASH_REMOVAL, ""));
    });
    return docList;
  }

  @Override
  public List<DocumentCountResponse> documentCountInfo(final DocumentCountRequest request) throws Exception {
    final String document = request.getDocument();
    final String keyword = request.getKeyword();

    final BoolQueryBuilder boolBuilder = QueryBuilder.buildBoolQueryBuilder(document, keyword);
    final String countQuery = SearchUtil.COUNT_QUERY_TEMPLATE.replaceAll("COUNT_QUERY", boolBuilder.toString());
    final SearchCountResponse response = executePostQuery(countQuery);
    return Arrays.asList(response).stream().map(resp -> {
      final DocumentCountResponse docObj = new DocumentCountResponse();
      docObj.setCount(resp.getCount());
      return docObj;
    }).collect(Collectors.toList());
  }

  public SearchCountResponse executePostQuery(final String jsonQuery) throws Exception {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.ALL));

    final HttpEntity<?> entity = new HttpEntity<String>(jsonQuery, headers);
    final List<String> urlList = SearchUtil.esUrlList(esConfiguration);
    final String esCountUrl = urlList.get(0) + "/" + esConfiguration.getDocumentIndex() + "/_doc/_count";
    final ResponseEntity<String> response = restTemplate.exchange(esCountUrl, HttpMethod.POST, entity, String.class);
    LOGGER.info("es-count-response :[{}]", response.getBody());
    return SearchUtil.readData(response.getBody(), SearchCountResponse.class);
  }

}
