package com.amaris.ai.cloud.search.services;

import java.util.List;
import com.amaris.ai.cloud.search.request.DocumentCountRequest;
import com.amaris.ai.cloud.search.response.DocumentCountResponse;

public interface DocumentCountService {

  List<DocumentCountResponse> documentCountAcrossIndex(final DocumentCountRequest request) throws Exception;

  List<DocumentCountResponse> documentCountInfo(final DocumentCountRequest request) throws Exception;
}
