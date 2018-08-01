package com.amaris.ai.cloud.search.configuration;

import static com.amaris.ai.cloud.search.util.SearchUtil.DEFAULT_INDEX_TYPE;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.amaris.ai.cloud.search.BaseSetup;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.util.QueryBuilder;
import com.amaris.ai.cloud.search.util.SearchUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
public class ESSingleFieldQueryTest extends BaseSetup {

  private static final Logger LOGGER = LoggerFactory.getLogger(ESSingleFieldQueryTest.class);

  static RestHighLevelClient esclient;
  final static String INDEX_NAME = "searchpdf"; 

  @BeforeClass
  public static void setup() {
    esclient = new RestHighLevelClient(RestClient.builder(new HttpHost(ES_HOST, ES_PORT, PROTOCOL)));
  }

  @AfterClass
  public static void destroy() throws Exception {
    esclient.close();
  }

  @Test
  public void testTermAggregrationQuery() throws Exception {
    final SearchSourceBuilder searchSourceBuilder = QueryBuilder.defaultSourceBuilder(0);

    final TermsAggregationBuilder aggregation = AggregationBuilders.terms("unique_document").field("document");
    searchSourceBuilder.aggregation(aggregation);

    final SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
    searchRequest.types(DEFAULT_INDEX_TYPE);
    searchRequest.source(searchSourceBuilder);
    LOGGER.info("\n" + searchRequest);

    final SearchResponse response = esclient.search(searchRequest);
    LOGGER.info("\n" + response.toString().replaceAll("\\s+", " ").trim());
    final JsonNode fullDocument = SearchUtil.objectMapper().readTree(response.toString().replaceAll("\\s+", " ").trim().getBytes());

    final String jsondata = SearchUtil.parseAggregateDocuments(fullDocument);
    final TypeReference<List<DocumentCountResponse>> mapType = new TypeReference<List<DocumentCountResponse>>() {};
    final List<DocumentCountResponse> docList = SearchUtil.readListData(jsondata, mapType);
    SearchUtil.writeJsonData(docList);
  }


  // @Test
  public void executeESQuery() throws Exception {
    final BoolQueryBuilder boolBuilder = QueryBuilder.buildBoolQueryBuilder("content", "de");
    final SearchSourceBuilder sourceBuilder = QueryBuilder.defaultSourceBuilder(50);
    sourceBuilder.query(boolBuilder);

    LOGGER.info("BoolQueryBuilder\n" + boolBuilder);

    SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
    searchRequest.types(DEFAULT_INDEX_TYPE);
    searchRequest.source(sourceBuilder);

    LOGGER.info("\n" + searchRequest);

    final SearchResponse response = esclient.search(searchRequest);
    final AtomicInteger tracker = new AtomicInteger();
    Arrays.asList(response.getHits().getHits()).forEach(searchhit -> {
      LOGGER.info("" + searchhit.getSourceAsString().replaceAll("\\s+", "\\s").trim());
      tracker.incrementAndGet();
    });
    LOGGER.info("Total number of documents retrieved : [{}] " + tracker.get());
  }

  // @Test
  public void listDocument() throws Exception {
    final SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
    searchRequest.types(DEFAULT_INDEX_TYPE);

    final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    searchRequest.source(searchSourceBuilder);

    final SearchResponse response = esclient.search(searchRequest);
    LOGGER.info("" + response);

    final org.elasticsearch.search.SearchHit[] searchHits = response.getHits().getHits();
    final AtomicInteger tracker = new AtomicInteger();
    Arrays.asList(searchHits).forEach(searchHit -> {
      LOGGER.info(searchHit.getSourceAsString().replaceAll("\\s+", " ").trim());
      tracker.incrementAndGet();
    });
    LOGGER.info("Total number of documents retrieved : [{}] " + tracker.get());
  }

}
