package com.amaris.ai.cloud.web.service.impl;

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
import com.amaris.ai.cloud.web.model.DocumentAudit;
import com.amaris.ai.cloud.web.service.DocumentAuditService;
import com.amaris.ai.cloud.web.util.WebUtil;

@Service
public class DocumentAuditServiceImpl implements DocumentAuditService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentAuditService.class);

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public List<DocumentAudit> listDocumentAudits(final String domain) {
    return this.listDocumentAudits();
  }

  @Override
  public List<DocumentAudit> listDocumentAudits() {
    final List<DocumentAudit> docList = new ArrayList<>();
    final Resource resource = this.resourceLoader.getResource("classpath:mock-data/download_document.json");
    try {
      try (final InputStream ios = resource.getInputStream();) {
        final String jsondata = IOUtils.toString(ios, Charset.defaultCharset());
        docList.addAll(WebUtil.readListData(jsondata, DocumentAudit.class));
      }
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return docList;
  }

}

