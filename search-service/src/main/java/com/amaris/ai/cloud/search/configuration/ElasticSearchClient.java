package com.amaris.ai.cloud.search.configuration;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchClient.class);

  @Autowired
  private ESConfiguration elasticClientConfiguration;

  private RestHighLevelClient restHighLevelClient;

  public RestHighLevelClient searchClient() {
    return this.restHighLevelClient;
  }

  @PostConstruct
  private void postConstruct() {
    try {
      final RestClientBuilder builder = RestClient.builder(getHostConfigArray());
      if (elasticClientConfiguration.isAuthenticated()) {
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
          @Override
          public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
            return httpClientBuilder.setDefaultCredentialsProvider(getCredentialProvider());
          }
        });
      }
      restHighLevelClient = new RestHighLevelClient(builder);
    } catch (final Exception ex) {
      throw new RuntimeException("Error in initializing rest high level client " + ex, ex);
    }
  }

  private HttpHost[] getHostConfigArray() {
    final List<HttpHost> httpHostL = elasticClientConfiguration.getElasticHostConfigs().stream().map(hostConfig -> {
      return new HttpHost(hostConfig.getHost(), hostConfig.getPort(), elasticClientConfiguration.getProtocol());
    }).collect(Collectors.toList());
    final HttpHost[] hostArray = new HttpHost[httpHostL.size()];
    return httpHostL.toArray(hostArray);
  }

  private CredentialsProvider getCredentialProvider() {
    final UsernamePasswordCredentials credential =
        new UsernamePasswordCredentials(elasticClientConfiguration.getUserid(), elasticClientConfiguration.getPassword());

    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY, credential);
    return credentialsProvider;
  }

  @PreDestroy
  private void preDestroy() {
    try {
      restHighLevelClient.close();
    } catch (final Exception ex) {
      LOGGER.error("Error in closing elastic high level client " + ex, ex);
    }
  }

}
