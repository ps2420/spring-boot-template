package com.amaris.ai.cloud.search.util;

import static com.amaris.ai.cloud.search.util.SearchUtil.CONTENT;
import static com.amaris.ai.cloud.search.util.SearchUtil.DOCUMENT;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.StringUtils;

public class QueryBuilder {

  public static String WILDCARD_STAR = "*";

  /**
   * http://www.brightmarbles.io/news/blog-posts/a-quick-guide-to-elasticsearch-java-clients-part-3/
   * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-search.html
   * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/6.0/java-rest-high-java-builders.html
   * 
   * @return
   */
  public static SearchSourceBuilder defaultSourceBuilder(final Integer from, final Integer to) {
    final SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.timeout(new TimeValue(2000, TimeUnit.SECONDS));
    sourceBuilder.from(from);
    sourceBuilder.size(to);
    sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
    return sourceBuilder;
  }

  public SearchRequest defaultSearchRequest(final String indexName, final SearchSourceBuilder sourceBuilder) {
    final SearchRequest searchRequest = new SearchRequest(indexName);
    searchRequest.source(sourceBuilder);
    return searchRequest;
  }

  public static BoolQueryBuilder buildBoolQueryBuilder(final String document, final String searchTerm) {
    final BoolQueryBuilder boolQuery = new BoolQueryBuilder();
    if (!StringUtils.isEmpty(document)) {
      boolQuery.must(QueryBuilders.wildcardQuery(DOCUMENT, WILDCARD_STAR + document.trim().toLowerCase() + WILDCARD_STAR));
    }
    Arrays.asList(searchTerm.split(",")).forEach(_keyword -> {
      final BoolQueryBuilder _boolQuery = new BoolQueryBuilder();
      Arrays.asList(_keyword.trim().toLowerCase().split(" ")).forEach(keyword -> {
        _boolQuery.should(QueryBuilders.wildcardQuery(CONTENT, WILDCARD_STAR + keyword.trim().toLowerCase() + WILDCARD_STAR));
        _boolQuery.should(QueryBuilders.queryStringQuery(CONTENT + ":" + keyword));
      });
      boolQuery.must(_boolQuery);
    });
    return boolQuery;
  }
  
}
