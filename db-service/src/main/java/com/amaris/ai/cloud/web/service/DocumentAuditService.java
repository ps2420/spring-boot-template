package com.amaris.ai.cloud.web.service;

import java.util.List;
import com.amaris.ai.cloud.web.model.DocumentAudit;

public interface DocumentAuditService {

  List<DocumentAudit> listDocumentAudits(final String domain);

  List<DocumentAudit> listDocumentAudits();

}
