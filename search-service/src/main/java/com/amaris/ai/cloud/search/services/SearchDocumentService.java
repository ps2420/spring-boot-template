package com.amaris.ai.cloud.search.services;

import java.util.List;
import com.amaris.ai.cloud.search.model.SearchDocument;
import com.amaris.ai.cloud.search.model.SearchDocumentRequest;

public interface SearchDocumentService {

  List<SearchDocument> listSearchDocument(final String product, final String keyword);
  
  List<SearchDocument> mockSearchDocument(final String product, final String keyword);

  List<SearchDocument> listDocuments(SearchDocumentRequest searchDocumentRequest);

}
