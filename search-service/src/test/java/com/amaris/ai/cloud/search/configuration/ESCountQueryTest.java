package com.amaris.ai.cloud.search.configuration;

import static com.amaris.ai.cloud.search.util.QueryBuilder.WILDCARD_STAR;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import com.amaris.ai.cloud.search.BaseSetup;
import com.amaris.ai.cloud.search.response.SearchCountResponse;
import com.amaris.ai.cloud.search.util.QueryBuilder;
import com.amaris.ai.cloud.search.util.SearchUtil;

@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
public class ESCountQueryTest extends BaseSetup {

  private static final Logger LOGGER = LoggerFactory.getLogger(ESCountQueryTest.class);

  static RestHighLevelClient esclient;
  final static String INDEX_NAME = "searchpdf"; 

  static String COUNT_QUERY_TEMPLATE = "{\n\"query\":\nCOUNT_QUERY\n}";

  @Test
  public void countQuery_whenSearchParamIsValid() throws Exception {
    final BoolQueryBuilder boolBuilder = QueryBuilder.buildBoolQueryBuilder("content", "provisions"); 
    final String esQuery = boolBuilder.toString();
    final String countQuery = COUNT_QUERY_TEMPLATE.replaceAll("COUNT_QUERY", esQuery);
    final SearchCountResponse response = executeQuery(countQuery);
    SearchUtil.writeJsonData(response);
    assertNotNull(response);
    assertTrue(response.getCount() > 0);
  }

  @Test
  public void countQuery_whenDocumentNameIsProvided() throws Exception {
    final String field = "content", keyword = "provisions";
    final BoolQueryBuilder boolQuery = new BoolQueryBuilder();
    boolQuery.must(QueryBuilders.wildcardQuery("document", WILDCARD_STAR + "*mediacorp.pdf".trim().toLowerCase() + WILDCARD_STAR));
    boolQuery.must(QueryBuilders.wildcardQuery(field, WILDCARD_STAR + keyword.trim().toLowerCase() + WILDCARD_STAR));
    boolQuery.must(QueryBuilders.queryStringQuery(field + ":" + keyword));

    final String esQuery = boolQuery.toString();
    final String countQuery = COUNT_QUERY_TEMPLATE.replaceAll("COUNT_QUERY", esQuery);
    final SearchCountResponse response = executeQuery(countQuery);
    SearchUtil.writeJsonData(response);
    assertNotNull(response);
    assertTrue(response.getCount() > 0);
  }

  public static SearchCountResponse executeQuery(final String jsonQuery) throws Exception {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final HttpEntity<?> entity = new HttpEntity<>(headers);

    final RestTemplate restTemplate = new RestTemplate();
    final String esCountUrl = esCountURL(INDEX_NAME, SearchUtil.DEFAULT_INDEX_TYPE);
    LOGGER.info("\n-URL:[{}], \nJSON-Query:[{}]", esCountUrl, jsonQuery);
    final ResponseEntity<String> response = restTemplate.exchange(esCountUrl, HttpMethod.GET, entity, String.class, jsonQuery);
    return SearchUtil.readData(response.getBody(), SearchCountResponse.class);

  }
}


