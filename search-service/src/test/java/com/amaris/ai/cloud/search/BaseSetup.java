package com.amaris.ai.cloud.search;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class BaseSetup {

  public final static String ES_HOST = "192.168.1.119";
  public final static Integer ES_PORT = 9200;
  public final static String PROTOCOL = "http";

  public final static String DEBT_EQUITY_RATIO = "debt_equity_ratio";

  static RestHighLevelClient esclient;

  public static void setup() {
    esclient = new RestHighLevelClient(RestClient.builder(new HttpHost(ES_HOST, ES_PORT, "http")));
    System.setProperty("EUREKA_CLIENT_ENABLED", "false");
  }

  public static void destroy() throws Exception {
    esclient.close();
  }

  public static String esCountURL(final String indexname, final String indexType) {
    return PROTOCOL + "://" + ES_HOST + ":9200/" + indexname + "/" + indexType + "/_count";
  }
 

}
