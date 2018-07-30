package com.amaris.ai.cloud.db.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.db.model.FileItem;
import com.amaris.ai.cloud.db.model.FinacialProduct;
import com.amaris.ai.cloud.db.service.HtmlContentService;
import com.amaris.ai.cloud.db.util.CommonUtil;

@Service
public class HtmlContentServiceImpl implements HtmlContentService {

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public List<FinacialProduct> listFinancialProducts() {
    final String jsondata = CommonUtil.loadJsonData(resourceLoader, CommonUtil.MOCK_CONTENT + "financial-product.json");
    return CommonUtil.readListData(jsondata, FinacialProduct.class);
  }

  @Override
  public List<FileItem> listFilesByProduct(final String product) {
    final String jsondata = CommonUtil.loadJsonData(resourceLoader, CommonUtil.MOCK_CONTENT + "file-list.json");
    return CommonUtil.readListData(jsondata, FileItem.class);
  }

}
