package com.amaris.ai.cloud.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.amaris.ai.cloud.web.model.DocumentAudit;
import com.amaris.ai.cloud.web.service.DocumentAuditService;

@RestController
@RequestMapping("/docaudit/")
public class DocumentAuditController {

  @Autowired
  private DocumentAuditService documentAuditService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public List<DocumentAudit> listDocumentAudits(@RequestParam(name = "product", defaultValue = "") final String product) {
    return documentAuditService.listDocumentAudits(product);
  }

}
