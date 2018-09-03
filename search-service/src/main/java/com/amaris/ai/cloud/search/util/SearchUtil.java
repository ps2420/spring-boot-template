package com.amaris.ai.cloud.search.util;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import com.amaris.ai.cloud.search.configuration.ESConfiguration;
import com.amaris.ai.cloud.search.request.SearchDocumentRequest;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchUtil.class);

  private static ObjectMapper objectMapper;
  public static final String HTML_CONTENT = "classpath:html-contents/";
  public static final String MOCK_CONTENT = "classpath:mock-data/";

  public static final String DEFAULT_INDEX_TYPE = "_doc";
  public static final String SLASH_REMOVAL = ".*\\/";
  public static final String COUNT_QUERY_TEMPLATE = "{\n\"query\":\nCOUNT_QUERY\n}";

  public static final String CONTENT = "content";
  public static final String DOCUMENT = "document";

  static {
    objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static ObjectMapper objectMapper() {
    return SearchUtil.objectMapper;
  }

  public static void writeJsonData(final Object data) {
    try {
      final String jsonData = objectMapper().writeValueAsString(data);
      LOGGER.info("Class:[{}], data:[{}]", data.getClass(), jsonData);
    } catch (final Exception ex) {

    }
  }

  public static <T> T readData(final String jsondata, final Class<T> clazz) {
    try {
      return objectMapper().readValue(jsondata.getBytes(), clazz);
    } catch (final Exception ex) {
      LOGGER.error("Error in converting to class:[{}], json-data:[{}]" + ex, clazz, jsondata, ex);
    }
    return null;
  }

  public static <T> List<T> readListData(final String jsondata, final TypeReference<List<T>> mapType) {
    try {
      return SearchUtil.objectMapper.readValue(jsondata.getBytes(), mapType);
    } catch (final Exception ex) {
      LOGGER.error("Error in converting to class:[{}], json-data:[{}]", mapType, jsondata);
    }
    return new ArrayList<T>();
  }

  public static String loadJsonData(final ResourceLoader resourceLoader, final String filename) {
    try {
      final Resource resource = resourceLoader.getResource(filename);
      try (final InputStream ios = resource.getInputStream();) {
        return IOUtils.toString(ios, Charset.defaultCharset());
      }
    } catch (final Exception ex) {
      throw new RuntimeException("Error in loading data from file :" + filename, ex);
    }
  }

  public static String parseAggregateDocuments(final JsonNode jsonNode) {
    String jsonnode = null;
    final JsonNode agJsonNode = jsonNode.get("aggregations");
    if (agJsonNode.get("sterms#unique_document") != null && agJsonNode != null) {
      final JsonNode bucketNode = agJsonNode.get("sterms#unique_document").get("buckets");
      if (bucketNode != null) {
        jsonnode = bucketNode.toString().replaceAll("\"key\"", "\"name\"").replaceAll("doc_count", "count");
      }
    }
    return jsonnode;
  }

  public static List<String> esUrlList(final ESConfiguration configuration) {
    return configuration.getElasticHostConfigs().stream().map(host -> {
      return (configuration.getProtocol() + "://" + host.getHost() + ":" + host.getPort());
    }).collect(Collectors.toList());
  }

  public static SearchDocumentRequest prepareSearchRequest(final String document, final String keyword) {
    final SearchDocumentRequest request = new SearchDocumentRequest();
    request.setDocument(document);
    request.setKeyword(keyword);
    return request;
  }
}


