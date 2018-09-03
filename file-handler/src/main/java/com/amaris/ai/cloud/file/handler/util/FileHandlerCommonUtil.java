package com.amaris.ai.cloud.file.handler.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileHandlerCommonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileHandlerCommonUtil.class);

	private static ObjectMapper objectMapper;
	public static final String MOCK_CONTENT = "classpath:mock-data/";

	static {
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static ObjectMapper objectMapper() {
		return FileHandlerCommonUtil.objectMapper;
	}

	public static void writeJsonData(final Object data) {
		try {
			final String jsonData = FileHandlerCommonUtil.objectMapper.writeValueAsString(data);
			LOGGER.info("Class:[{}], data:[{}]", data.getClass(), jsonData);
		} catch (final Exception ex) {
		}
	}

	public static <T> T readData(final String jsondata, final Class<T> clazz) {
		try {
			return FileHandlerCommonUtil.objectMapper.readValue(jsondata.getBytes(), clazz);
		} catch (final Exception ex) {
			LOGGER.error("Error in converting to class:[{}], json-data:[{}]", clazz, jsondata);
		}
		return null;
	}

	public static Map<String, String> readDataIntoMap(final String jsondata) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			final TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
			};
			jsonMap = FileHandlerCommonUtil.objectMapper().readValue(jsondata, typeRef);
		} catch (final Exception ex) {
			LOGGER.error("Error into converting jsondata :[{}] into map", jsondata);
		}
		return jsonMap;
	}

	public static void logJsonData(final Object value) {
		try {
			final String jsondata = objectMapper().writeValueAsString(value);
			LOGGER.info("json-data:[{}]", jsondata);
		} catch (final Exception ex) {
			LOGGER.error("Error in writing json-data : " + ex, ex);
		}
	}
}
