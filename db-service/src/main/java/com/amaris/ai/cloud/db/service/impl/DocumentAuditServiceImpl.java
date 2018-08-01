package com.amaris.ai.cloud.db.service.impl;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.DocumentAuditService;
import com.amaris.ai.cloud.db.service.KafkaService;
import com.amaris.ai.cloud.db.util.CommonUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class DocumentAuditServiceImpl implements DocumentAuditService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentAuditService.class);

  @Autowired
  private ResourceLoader resourceLoader;

  @Autowired
  private KafkaService kafkaService;

  @Override
  public List<DocumentAudit> listDocumentAudits(final String domain) {
    return this.listDocumentAudits();
  }

  @Override
  public List<DocumentAudit> listDocumentAudits() {
    final List<DocumentAudit> docList = new ArrayList<>();
    final Resource resource = this.resourceLoader.getResource(CommonUtil.MOCK_CONTENT + "download_document.json");
    try {
      try (final InputStream ios = resource.getInputStream();) {
        final String jsondata = IOUtils.toString(ios, Charset.defaultCharset());
        final TypeReference<List<DocumentAudit>> mapType = new TypeReference<List<DocumentAudit>>() {};
        docList.addAll(CommonUtil.readListData(jsondata, mapType));
      }
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return docList;
  }

  @Override
  public void auditDocument(final DocumentAudit docAudit) {
    try {
      kafkaService.sendDocumentAuditEvent(docAudit);
    } catch (final Exception ex) {
      LOGGER.error("Error in auditing document " + ex, ex);
      throw new RuntimeException("Error in auditing document " + ex, ex);
    }
  }

}

