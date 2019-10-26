package com.pk.ai.cloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerServiceUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceUtil.class);

	private static ObjectMapper objectMapper;
	public static final String MOCK_CONTENT = "classpath:mock-data/";

	static {
		objectMapper = new ObjectMapper();
		// objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static ObjectMapper objectMapper() {
		return CustomerServiceUtil.objectMapper;
	}

	public static String writeJsonData(final Object data) {
		String jsonData = "";
		try {
			jsonData = CustomerServiceUtil.objectMapper.writeValueAsString(data);
			LOGGER.info("Class:[{}], data:[{}]", data.getClass(), jsonData);
		} catch (final Exception ex) {
		}
		return jsonData;
	}

	public static <T> T readData(final String jsondata, final Class<T> clazz) {
		try {
			return objectMapper().readValue(jsondata.getBytes(), clazz);
		} catch (final Exception ex) {
			LOGGER.error("Error in converting to class:[{}], json-data:[{}]" + ex, clazz, jsondata, ex);
		}
		return null;
	}

}