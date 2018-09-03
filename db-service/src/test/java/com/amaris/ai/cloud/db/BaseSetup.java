package com.amaris.ai.cloud.db;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.amaris.ai.cloud.db.model.DocumentAudit;

public class BaseSetup {

  public static String RANDOM = "XXX";
  public static String AMARIS_USER = "amaris";
  public static String RANDOM_DOCUMENT = "XXX" + ".pdf";
  
  public final static String BOOTSTRAP_SERVERS = "localhost:9092";
  public final static String TOPIC = "document_upload_event";

  @BeforeClass
  public static void setup() {
    System.setProperty("EUREKA_CLIENT_ENABLED", "false");
    System.setProperty("LOG_DIR", System.getProperty("java.io.tmpdir"));
    System.setProperty("APP_NAME", java.util.UUID.randomUUID().toString());
    System.setProperty("spring.cloud.discovery.enabled", "false");
    System.setProperty("spring.cloud.refresh.refreshable", "none");
  }

  @AfterClass
  public static void destroy() throws Exception {}

  public static DocumentAudit mockDocumentAudit() {
    final DocumentAudit docAudit = new DocumentAudit();
    docAudit.setProduct(BaseSetup.RANDOM);
    docAudit.setDocument(BaseSetup.RANDOM_DOCUMENT);
    docAudit.setComments(BaseSetup.RANDOM);
    docAudit.setUploadedBy(BaseSetup.AMARIS_USER);
    return docAudit;
  }

}
