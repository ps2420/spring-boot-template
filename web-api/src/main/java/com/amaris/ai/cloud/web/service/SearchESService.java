package com.amaris.ai.cloud.web.service;

import java.util.List;
import com.amaris.ai.cloud.web.request.SearchDocumentRequest;
import com.amaris.ai.cloud.web.response.DocumentCountResponse;
import com.amaris.ai.cloud.web.response.SearchDocumentResponse;

public interface SearchESService {

  List<SearchDocumentResponse> listContent(final String product, final String keyword);

  List<SearchDocumentResponse> listDocuments(final SearchDocumentRequest searchDocumentRequest);

  List<DocumentCountResponse> documentCountInfo(final String product) throws Exception;

}
