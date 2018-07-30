package com.amaris.ai.cloud.web.service;

import java.util.List;
import com.amaris.ai.cloud.web.model.FileItem;
import com.amaris.ai.cloud.web.model.FinacialProduct;

public interface HtmlContentService {

  List<FinacialProduct> listFinancialProducts();

  List<FileItem> listFilesByProduct(String product);
}
