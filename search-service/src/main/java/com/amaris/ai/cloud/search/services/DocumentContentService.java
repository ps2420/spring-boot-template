package com.amaris.ai.cloud.search.services;

import java.util.List;
import com.amaris.ai.cloud.search.request.SearchDocumentRequest;
import com.amaris.ai.cloud.search.response.SearchDocumentResponse;

public interface DocumentContentService {

  List<SearchDocumentResponse> searchDocuments(final SearchDocumentRequest searchDocumentRequest);

}
