package com.amaris.ai.cloud.search.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class MultipleFieldQueryTest {
 
  static RestHighLevelClient esclient;
  final static String INDEX_NAME = "searchpdf";
   
  static void initESClient() {
    esclient = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.1.119", 9200, "http")));
  }

  public static void main(String[] args) throws Exception {

  }

}
