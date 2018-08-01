package com.amaris.ai.cloud.search.util;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class QueryBuilder {

  public static String WILDCARD_STAR = "*";

  /**
   * http://www.brightmarbles.io/news/blog-posts/a-quick-guide-to-elasticsearch-java-clients-part-3/
   * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-search.html
   * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.0/java-rest-high-java-builders.html
   * 
   * @return
   */
  public static SearchSourceBuilder defaultSourceBuilder(final Integer resultSize) {
    final SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.timeout(new TimeValue(2000, TimeUnit.SECONDS));
    sourceBuilder.from(0);
    sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
    sourceBuilder.size(resultSize);
    return sourceBuilder;
  }

  public SearchRequest defaultSearchRequest(final String indexName, final SearchSourceBuilder sourceBuilder) {
    final SearchRequest searchRequest = new SearchRequest(indexName);
    // searchRequest.types("_doc");
    searchRequest.source(sourceBuilder);
    return searchRequest;
  }

  public static BoolQueryBuilder buildBoolQueryBuilder(final String field, final String searchTerm) {
    final BoolQueryBuilder boolQuery = new BoolQueryBuilder();
    Arrays.asList(searchTerm.split(",")).forEach(_keyword -> {
      Arrays.asList(_keyword.trim().toLowerCase().split(" ")).forEach(keyword -> {
        boolQuery.must(QueryBuilders.wildcardQuery(field, WILDCARD_STAR + keyword.trim().toLowerCase() + WILDCARD_STAR));
        boolQuery.must(QueryBuilders.queryStringQuery(field + ":" + keyword));
      });
    });
    return boolQuery;
  }
  
  public static BoolQueryBuilder buildMustQueryBuilder(final String field, final String searchTerm) {
    final BoolQueryBuilder query = new BoolQueryBuilder();
    Arrays.asList(searchTerm.split(" ")).forEach(keyword -> {
      query.must(QueryBuilders.wildcardQuery(field, WILDCARD_STAR + keyword.trim().toLowerCase() + WILDCARD_STAR));
      query.must(QueryBuilders.queryStringQuery(field + ":" + keyword));
    });
    return query;
  }

}
