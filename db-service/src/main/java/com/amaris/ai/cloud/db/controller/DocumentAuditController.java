package com.amaris.ai.cloud.db.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.DocumentAuditService;

@RestController
@RequestMapping("/docaudit/")
public class DocumentAuditController {

  @Autowired
  private DocumentAuditService documentAuditService;

  @RequestMapping(value = {"/listDocumentAudits"}, method = RequestMethod.GET)
  public List<DocumentAudit> listDocumentAudits() throws Exception {
    return documentAuditService.listDocumentAudits();
  }

  @PostMapping(value = {"/audit"})
  public DocumentAudit auditDocument(final @RequestBody DocumentAudit docAudit) throws Exception {
    return documentAuditService.auditDocument(docAudit);
  }

}
