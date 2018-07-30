package com.amaris.ai.cloud.db.service;

import java.util.List;
import com.amaris.ai.cloud.db.model.FileItem;
import com.amaris.ai.cloud.db.model.FinacialProduct;

public interface HtmlContentService {

  List<FinacialProduct> listFinancialProducts();

  List<FileItem> listFilesByProduct(String product);
}
