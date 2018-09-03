package com.amaris.ai.cloud.file.handler.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amaris.ai.cloud.file.handler.configuration.EnvironmentMatrix;

@Component
public class URLBuilder {

	@Autowired
	private EnvironmentMatrix envMatrix;

	private String urlPrefix() {
		return envMatrix.getProtocol() + "://";
	}

	private String dbUrlPrefix() {
		return urlPrefix() + envMatrix.getDbReference();
	}

	public String documentAuditUrl() {
		return dbUrlPrefix() + "/docaudit/audit";
	}

}
