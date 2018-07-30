package com.amaris.ai.cloud.web.service;

import java.util.List;
import com.amaris.ai.cloud.web.model.SearchDocument;

public interface SearchESService {

  List<SearchDocument> listSearchDocument(final String product, final String keyword);

}
