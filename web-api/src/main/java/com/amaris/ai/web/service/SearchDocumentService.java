package com.amaris.ai.web.service;

import java.util.List;
import com.amaris.ai.web.model.SearchDocument;

public interface SearchDocumentService {

  List<SearchDocument> listSearchDocument(final String product, final String keyword);
}
