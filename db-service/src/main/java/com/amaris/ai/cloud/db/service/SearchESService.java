package com.amaris.ai.cloud.db.service;

import java.util.List;
import com.amaris.ai.cloud.db.model.SearchDocument;

public interface SearchESService {

  List<SearchDocument> listSearchDocument(final String product, final String keyword);

}
