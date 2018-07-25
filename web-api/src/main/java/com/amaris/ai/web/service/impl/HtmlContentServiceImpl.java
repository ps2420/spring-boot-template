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
import com.amaris.ai.web.model.FinacialProduct;
import com.amaris.ai.web.service.HtmlContentService;
import com.amaris.ai.web.util.WebUtil;

@Service
public class HtmlContentServiceImpl implements HtmlContentService {

  private static final Logger LOGGER = LoggerFactory.getLogger(HtmlContentService.class);

  private final String HTML_CONTENT = "classpath:html-contents/";

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public List<FinacialProduct> listFinancialProducts() {
    final List<FinacialProduct> productList = new ArrayList<>();
    final Resource resource = this.resourceLoader.getResource(HTML_CONTENT + "financial-product.json");
    try {
      try (final InputStream ios = resource.getInputStream();) {
        final String jsondata = IOUtils.toString(ios, Charset.defaultCharset());
        productList.addAll(WebUtil.readListData(jsondata, FinacialProduct.class));
      }
    } catch (final Exception ex) {
      LOGGER.error("Error in converting json data back to object " + ex, ex);
    }
    return productList;
  }

}
