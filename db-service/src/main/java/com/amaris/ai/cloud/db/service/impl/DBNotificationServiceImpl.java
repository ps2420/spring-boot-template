package com.amaris.ai.cloud.db.service.impl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amaris.ai.cloud.db.config.KafkaConfiguration;
import com.amaris.ai.cloud.db.model.DocumentAudit;
import com.amaris.ai.cloud.db.service.DBNotificationService;
import com.amaris.ai.cloud.db.util.CommonUtil;

@Service
public class DBNotificationServiceImpl implements DBNotificationService {

  @Autowired
  private KafkaProducer<String, Object> kafkaProducer;

  @Autowired
  private KafkaConfiguration kConfiguration;

  @Override
  public DocumentAudit auditDocument(final DocumentAudit documentAudit) throws Exception {
    final ProducerRecord<String, Object> record = new ProducerRecord<String, Object>(kConfiguration.getDocKafkaAudit(),
        java.util.UUID.randomUUID().toString(), CommonUtil.objectMapper().writeValueAsString(documentAudit));
    kafkaProducer.send(record);
    return documentAudit;
  }

}
