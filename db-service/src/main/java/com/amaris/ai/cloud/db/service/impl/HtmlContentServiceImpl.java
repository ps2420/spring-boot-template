package com.amaris.ai.cloud.db.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.db.model.FileItem;
import com.amaris.ai.cloud.db.model.FinacialProduct;
import com.amaris.ai.cloud.db.service.HtmlContentService;
import com.amaris.ai.cloud.db.util.CommonUtil;
import com.fasterxml.jackson.core.type.TypeReference;

@Service
public class HtmlContentServiceImpl implements HtmlContentService {

  @Autowired
  private ResourceLoader resourceLoader;

  @Override
  public List<FinacialProduct> listFinancialProducts() {
    final String jsondata = CommonUtil.loadJsonData(resourceLoader, CommonUtil.MOCK_CONTENT + "financial-product.json");
    final TypeReference<List<FinacialProduct>> mapType = new TypeReference<List<FinacialProduct>>() {};
    return CommonUtil.readListData(jsondata, mapType);
  }

  @Override
  public List<FileItem> listFilesByProduct(final String product) {
    final String jsondata = CommonUtil.loadJsonData(resourceLoader, CommonUtil.MOCK_CONTENT + "file-list.json");
    final TypeReference<List<FileItem>> mapType = new TypeReference<List<FileItem>>() {};
    return CommonUtil.readListData(jsondata, mapType);
  }

}
