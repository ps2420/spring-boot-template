package com.amaris.ai.cloud.db;

import java.util.Calendar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.amaris.ai.cloud.db.model.DocumentAudit;

public class BaseSetup {

  public static String RANDOM = "XXX";
  public static String AMARIS_USER = "amaris";
  public static String RANDOM_DOCUMENT = "XXX" + ".pdf";
  
  public final static String BOOTSTRAP_SERVERS = "192.168.1.119:6667,192.168.1.35:6667";
  public final static String TOPIC = "document_upload_event"; //

  @BeforeClass
  public static void setup() {
    System.setProperty("EUREKA_CLIENT_ENABLED", "false");
    System.setProperty("LOG_DIR", System.getProperty("java.io.tmpdir"));
    System.setProperty("APP_NAME", java.util.UUID.randomUUID().toString());
    System.setProperty("spring.cloud.discovery.enabled", "false");
  }

  @AfterClass
  public static void destroy() throws Exception {}

  public static DocumentAudit mockDocumentAudit() {
    final DocumentAudit docAudit = new DocumentAudit();
    docAudit.setDocument(java.util.UUID.randomUUID().toString() + ".pdf");
    docAudit.setProduct(BaseSetup.RANDOM);
    docAudit.setDocument(BaseSetup.RANDOM_DOCUMENT);
    docAudit.setComments(BaseSetup.RANDOM);
    docAudit.setUploadedBy(BaseSetup.AMARIS_USER);
    docAudit.setUploadDate(Calendar.getInstance().getTime());
    return docAudit;
  }

}
