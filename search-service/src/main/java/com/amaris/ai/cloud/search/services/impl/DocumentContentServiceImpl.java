package com.amaris.ai.cloud.search.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.search.request.SearchDocumentRequest;
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;
import com.amaris.ai.cloud.search.services.DocumentContentService;
import com.amaris.ai.cloud.search.services.ElasticSearchService;

@Service
public class DocumentContentServiceImpl implements DocumentContentService {

  @Autowired
  private ElasticSearchService elasticSearchService;

  @Override
  public List<SearchDocumentResponse> searchDocuments(final SearchDocumentRequest searchDocumentRequest) {
    return elasticSearchService.searchDocuments(searchDocumentRequest);
  }

}
