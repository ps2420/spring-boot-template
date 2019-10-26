package com.pk.ai.cloud;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.pk.ai.cloud.util.CustomerServiceUtil;

public class BaseSetup {

	public static String RANDOM = "XXX";
	public static String AMARIS_USER = "amaris";
	public static String RANDOM_DOCUMENT = "XXX" + ".pdf";

	public final static String BOOTSTRAP_SERVERS = "localhost:9092";
	public final static String TOPIC = "document_upload_event";

	@BeforeClass
	public static void setup() {
		System.setProperty("EUREKA_CLIENT_ENABLED", "false");
		System.setProperty("LOG_DIR", System.getProperty("java.io.tmpdir"));
		System.setProperty("APP_NAME", java.util.UUID.randomUUID().toString());
		System.setProperty("spring.cloud.discovery.enabled", "false");
		System.setProperty("spring.cloud.refresh.refreshable", "none");
	}

	@AfterClass
	public static void destroy() throws Exception {
	}

	public static void consolelog(final Object payload) {
		CustomerServiceUtil.writeJsonData(payload);
	}

}
