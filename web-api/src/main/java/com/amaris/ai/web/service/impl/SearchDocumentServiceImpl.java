package com.amaris.ai.web.service.impl;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.amaris.ai.web.model.SearchDocument;
import com.amaris.ai.web.service.HtmlContentService;
import com.amaris.ai.web.service.SearchDocumentService;
import com.amaris.ai.web.util.WebUtil;

@Service
public class SearchDocumentServiceImpl implements SearchDocumentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(HtmlContentService.class);

  private final String HTML_CONTENT = "classpath:mock-data/";

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public List<SearchDocument> listSearchDocument(String product, String keyword) {
    final List<SearchDocument> searchResults = new ArrayList<>();
    final Resource resource = this.resourceLoader.getResource(HTML_CONTENT + "search-result.json");
    try {
      try (final InputStream ios = resource.getInputStream();) {
        final String jsondata = IOUtils.toString(ios, Charset.defaultCharset());
        searchResults.addAll(WebUtil.readListData(jsondata, SearchDocument.class));
      }
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return searchResults;
  }

}
