package com.amaris.ai.cloud.search.configuration;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.amaris.ai.cloud.search.ITTestSetup;
import com.amaris.ai.cloud.search.configuration.elastic.ElasticSearchClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class ElasticSearchClientTest {

  @Autowired
  private ElasticSearchClient elasticSearchClient;

  @Test
  public void testElasticClientInitialization() {
    assertNotNull(elasticSearchClient);
    assertNotNull(elasticSearchClient.searchClient());
  }


}
