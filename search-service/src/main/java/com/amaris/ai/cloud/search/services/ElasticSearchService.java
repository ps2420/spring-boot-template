package com.amaris.ai.cloud.search.services;

import java.util.List;
import com.amaris.ai.cloud.search.request.DocumentCountRequest;
import com.amaris.ai.cloud.search.request.SearchDocumentRequest;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;

public interface ElasticSearchService {

  List<SearchDocumentResponse> listContent(final String product, final String keyword);

  List<SearchDocumentResponse> listContent(final SearchDocumentRequest searchDocumentRequest);

  List<DocumentCountResponse> listContent(final DocumentCountRequest request);
  
}
