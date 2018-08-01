package com.amaris.ai.cloud.search.util;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(SearchUtil.class);

  private static ObjectMapper objectMapper;
  public static final String HTML_CONTENT = "classpath:html-contents/";
  public static final String MOCK_CONTENT = "classpath:mock-data/";

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
      LOGGER.error("Error in converting to class:[{}], json-data:[{}]" +ex , clazz, jsondata, ex);
    }
    return null;
  }

  public static <T> List<T> readListData(final String jsondata, final Class<T> clazz) {
    try {
      final TypeReference<List<T>> mapType = new TypeReference<List<T>>() {};
      return objectMapper().readValue(jsondata.getBytes(), mapType);
    } catch (final Exception ex) {
      LOGGER.error("Error in converting to class:[{}], json-data:[{}]", clazz, jsondata);
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
}
