package com.amaris.ai.cloud.db.service;

import java.util.List;
import com.amaris.ai.cloud.db.model.DocumentAudit;

public interface DocumentAuditService {

  List<DocumentAudit> listDocumentAudits(final String domain);

  List<DocumentAudit> listDocumentAudits();

}
