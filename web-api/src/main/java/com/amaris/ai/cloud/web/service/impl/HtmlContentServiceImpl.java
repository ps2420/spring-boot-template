package com.amaris.ai.cloud.web.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.web.model.FileItem;
import com.amaris.ai.cloud.web.model.FinacialProduct;
import com.amaris.ai.cloud.web.service.HtmlContentService;
import com.amaris.ai.cloud.web.util.WebUtil;

@Service
public class HtmlContentServiceImpl implements HtmlContentService {

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public List<FinacialProduct> listFinancialProducts() {
    final String jsondata = WebUtil.loadJsonData(resourceLoader, WebUtil.HTML_CONTENT + "financial-product.json");
    return WebUtil.readListData(jsondata, FinacialProduct.class);
  }

  @Override
  public List<FileItem> listFilesByProduct(final String product) {
    final String jsondata = WebUtil.loadJsonData(resourceLoader, WebUtil.MOCK_CONTENT + "file-list.json");
    return WebUtil.readListData(jsondata, FileItem.class);
  }

}
