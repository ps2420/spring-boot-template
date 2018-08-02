package com.amaris.ai.cloud.search;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseSetup {

  public final static String ES_HOST = "192.168.1.119";
  public final static Integer ES_PORT = 9200;
  public final static String PROTOCOL = "http";

  public final static String KEYWORD = "keyword";

  public final static String DEBT_EQUITY_RATIO = "debt_equity_ratio";

  static RestHighLevelClient esclient;

  @BeforeClass
  public static void setup() {
    esclient = new RestHighLevelClient(RestClient.builder(new HttpHost(ES_HOST, ES_PORT, "http")));
    System.setProperty("EUREKA_CLIENT_ENABLED", "false");
    System.setProperty("LOG_DIR", System.getProperty("java.io.tmpdir"));
    System.setProperty("APP_NAME", java.util.UUID.randomUUID().toString());
    System.setProperty("spring.cloud.discovery.enabled", "false");
  }

  @AfterClass
  public static void destroy() throws Exception {
    esclient.close();
  }

  public static String esCountURL(final String indexname, final String indexType) {
    return PROTOCOL + "://" + ES_HOST + ":9200/" + indexname + "/" + indexType + "/_count";
  }

  public static void main(String[] args) throws Exception {
    final String value = "/home/junda/Documents/debt_equity_ratio/data/etechaces.pdf";
    System.out.println(value.replaceAll(".*\\/", ""));
  }

}
