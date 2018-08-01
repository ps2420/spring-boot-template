package com.amaris.ai.cloud.search.services;

import java.util.List;
import com.amaris.ai.cloud.search.model.SearchDocument;

public interface ElasticSearchService {

  List<SearchDocument> listSearchDocument(final String product, final String keyword);
  
}
