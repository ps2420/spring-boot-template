package com.amaris.ai.cloud.search;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amaris.ai.cloud.search.util.QueryBuilder;

public class ESMainClientTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(ESMainClientTest.class);

  static RestHighLevelClient esclient;

  public static void main(String[] args) throws Exception {
    initESClient();
    // listDocument();
    executeESQuery();
    esclient.close();
  }

  static void initESClient() {
    esclient = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.1.119", 9200, "http")));
  }


  static void executeESQuery() throws Exception {
    final BoolQueryBuilder boolBuilder = QueryBuilder.buildBoolQueryBuilder("content", "de");
    final SearchSourceBuilder sourceBuilder = QueryBuilder.defaultSourceBuilder(50);
    sourceBuilder.query(boolBuilder);

    LOGGER.info("BoolQueryBuilder\n" + boolBuilder);

    SearchRequest searchRequest = new SearchRequest("search_pdf");
    searchRequest.types("search_pdf");
    searchRequest.source(sourceBuilder);

    final SearchResponse response = esclient.search(searchRequest);
    final AtomicInteger tracker = new AtomicInteger();
    Arrays.asList(response.getHits().getHits()).forEach(searchhit -> {
      LOGGER.info("" + searchhit.getSourceAsString().replaceAll("\\s+", "\\s").trim());
      tracker.incrementAndGet();
    });
    LOGGER.info("Total number of documents retrieved : [{}] " + tracker.get());
  }

  static void listDocument() throws Exception {
    final SearchRequest searchRequest = new SearchRequest("search_pdf");
    searchRequest.types("search_pdf");

    final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    searchRequest.source(searchSourceBuilder);

    final SearchResponse response = esclient.search(searchRequest);
    LOGGER.info("" + response);

    final org.elasticsearch.search.SearchHit[] searchHits = response.getHits().getHits();
    final AtomicInteger tracker = new AtomicInteger();
    Arrays.asList(searchHits).forEach(searchHit -> {
      //LOGGER.info(searchHit.getSourceAsString().replaceAll("\\s+", " ").trim());
      //tracker.incrementAndGet();
      LOGGER.info("Total number of documents retrieved : [{}] " + tracker.incrementAndGet());
    });
    LOGGER.info("Total number of documents retrieved : [{}] " + tracker.get());

  }

}
