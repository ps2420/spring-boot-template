package com.amaris.ai.cloud.db.service.impl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.db.config.KafkaConfiguration;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.KafkaService;
import com.amaris.ai.cloud.db.util.CommonUtil;

@Service
public class KafkaServiceImpl implements KafkaService {

  @Autowired
  private KafkaProducer<String, String> kafkaProducer;

  @Autowired
  private KafkaConfiguration kConfiguration;

  @Override
  public void sendDocumentAuditEvent(final DocumentAudit documentAudit) throws Exception {
    final ProducerRecord<String, String> record = new ProducerRecord<String, String>(kConfiguration.getFile_topic(),
        java.util.UUID.randomUUID().toString(), CommonUtil.objectMapper().writeValueAsString(documentAudit));
    kafkaProducer.send(record);
  }

}
