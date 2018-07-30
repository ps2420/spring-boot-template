package com.amaris.ai.cloud.db.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

  @RequestMapping(value = "/list/{product}", method = RequestMethod.GET)
  public List<DocumentAudit> listDocumentAudits(@PathVariable final String product) {
    return documentAuditService.listDocumentAudits(product);
  }

}
