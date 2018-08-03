package com.amaris.ai.cloud.db;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.util.CommonUtil;

public class ClassicKafkaProducer extends BaseSetup {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClassicKafkaProducer.class);

  private static KafkaProducer<String, String> createProducer() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    return new KafkaProducer<>(props);
  }

  public static void main(String... args) throws Exception {
    final KafkaProducer<String, String> producer = createProducer();
    final DocumentAudit documentAudit = mockDocumentAudit();
    final String json_data = CommonUtil.objectMapper().writeValueAsString(documentAudit);
    final String messageId = java.util.UUID.randomUUID().toString();
    producer.send(new ProducerRecord<String, String>(TOPIC, messageId, json_data));
    LOGGER.info("Message has been sent successfully:[{}] \n[{}] ", messageId, json_data);
    producer.close();
  }
}
