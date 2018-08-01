package com.amaris.ai.cloud.db.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.DocumentAuditService;
import com.amaris.ai.cloud.db.util.CommonUtil;

@RestController
@RequestMapping("/docaudit/")
public class DocumentAuditController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentAuditController.class);

  @Autowired
  private DocumentAuditService documentAuditService;

  @RequestMapping(value = {"/list/{product}", "/list"}, method = RequestMethod.GET)
  public List<DocumentAudit> listDocumentAudits(final @PathVariable Optional<String> productOpt) {
    final String product = productOpt.isPresent() ? productOpt.get() : "";
    return documentAuditService.listDocumentAudits(product);
  }

  @PostMapping(value = {"/audit"})
  public String auditDocument(final DocumentAudit docAudit) {
    documentAuditService.auditDocument(docAudit);
    return "Health - 200";
  }

  @RequestMapping(value = {"/sendDocument"}, method = RequestMethod.GET)
  public String sendDummyDocument() {
    final List<DocumentAudit> docList = documentAuditService.listDocumentAudits();
    final DocumentAudit docAudit = docList.get(0);
    docAudit.setId(java.util.UUID.randomUUID().toString());
    documentAuditService.auditDocument(docAudit);
    return "Health - 200";
  }


}
