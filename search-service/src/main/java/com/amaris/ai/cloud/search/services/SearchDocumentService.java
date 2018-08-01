package com.amaris.ai.cloud.search.services;

import java.util.List;
import com.amaris.ai.cloud.search.request.DocumentCountRequest;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;

public interface SearchDocumentService {

  List<DocumentCountResponse> documentCountInfo(final String product) throws Exception;

  List<DocumentCountResponse> listDocument(final DocumentCountRequest request) throws Exception ;
}
