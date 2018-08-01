package com.amaris.ai.cloud.search.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ESMultipleFieldQueryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(ESSingleFieldQueryMainTest.class);

  static RestHighLevelClient esclient;
  final static String INDEX_NAME = "searchpdf";
  final static String INDEX_TYPE = "_doc";

  static void initESClient() {
    esclient = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.1.119", 9200, "http")));
  }

  public static void main(String[] args) throws Exception {

  }

}
