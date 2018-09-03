package com.amaris.ai.cloud.db.dao.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.amaris.ai.cloud.db.dao.DBAuditDAO;
import com.amaris.ai.cloud.db.model.DocumentAudit;

@Component
public class DBAuditDAOImpl implements DBAuditDAO {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 
  final String INSERT_QUERY =
      "insert into DOCUMENT_AUDIT (ID, PRODUCT, DOCUMENT, COMMENTS, UPLOADED_BY, CREATED_DATE) values (:ID, :PRODUCT, :DOCUMENT, :COMMENTS, :UPLOADED_BY, :CREATED_DATE)";

  @Override
  @Transactional(rollbackFor = Exception.class)
  public DocumentAudit auditDocument(final DocumentAudit document) throws Exception {
    final Map<String, Object> paramMap = prepareParam(document);
    namedParameterJdbcTemplate.update(INSERT_QUERY, paramMap);
    return document;
  }

  private Map<String, Object> prepareParam(final DocumentAudit document) {
    final Map<String, Object> paramMap = new HashMap<String, Object>();
    document.setId(java.util.UUID.randomUUID().toString());
    paramMap.put("ID", document.getId());
    paramMap.put("PRODUCT", document.getProduct());
    paramMap.put("DOCUMENT", document.getDocument());
    paramMap.put("COMMENTS", document.getComments());
    paramMap.put("UPLOADED_BY", document.getUploadedBy());
    paramMap.put("CREATED_DATE", document.getCreatedDate());
    return paramMap;
  }

}
