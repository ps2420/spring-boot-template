package com.amaris.ai.cloud.search.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.search.configuration.elastic.ElasticSearchClient;
import com.amaris.ai.cloud.search.services.ElasticSearchService;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchService.class);

  @Autowired
  private ElasticSearchClient elasticSearchClient;

}
