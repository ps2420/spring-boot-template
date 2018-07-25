package com.amaris.ai.web.util;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebUtil.class);

  private static ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(Include.NON_NULL);

  }

  public static ObjectMapper objectMapper() {
    return WebUtil.objectMapper;
  }

  public static void writeJsonData(final Object data) {
    try {
      final String jsonData = WebUtil.objectMapper.writeValueAsString(data);
      LOGGER.info("Class:[{}], data:[{}]", data.getClass(), jsonData);
    } catch (final Exception ex) {
    }
  }

  public static <T> T readData(final String jsondata, final Class<T> clazz) {
    try {
      return WebUtil.objectMapper.readValue(jsondata.getBytes(), clazz);
    } catch (final Exception ex) {
      LOGGER.error("Error in converting to class:[{}], json-data:[{}]", clazz, jsondata);
    }
    return null;
  }

  public static <T> List<T> readListData(final String jsondata, final Class<T> clazz) {
    try {
      final TypeReference<List<T>> mapType = new TypeReference<List<T>>() {};
      return WebUtil.objectMapper.readValue(jsondata.getBytes(), mapType);
    } catch (final Exception ex) {
      LOGGER.error("Error in converting to class:[{}], json-data:[{}]", clazz, jsondata);
    }
    return new ArrayList<T>();
  }
}
