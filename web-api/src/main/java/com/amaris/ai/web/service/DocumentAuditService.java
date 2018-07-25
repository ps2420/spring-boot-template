package com.amaris.ai.web.service;

import java.util.List;
import com.amaris.ai.web.model.DocumentAudit;

public interface DocumentAuditService {

  List<DocumentAudit> listDocumentAudits(final String domain);

  List<DocumentAudit> listDocumentAudits();

}
