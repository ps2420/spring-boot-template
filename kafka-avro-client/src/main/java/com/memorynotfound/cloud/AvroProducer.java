package com.memorynotfound.cloud;

import com.cloudurable.phonebook.Employee;
import com.cloudurable.phonebook.PhoneNumber;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import java.util.Properties;
import java.util.stream.IntStream;

public class AvroProducer {

  private static Producer<Long, Employee> createProducer() {
    Properties props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.119:6067,192.168.1.35:6067");
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "AvroProducer");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
    // Configure the KafkaAvroSerializer.
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
    // Schema Registry location.
    props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
    return new KafkaProducer<>(props);
  }

  private final static String TOPIC = "new-employees";

  public static void main(String... args) {
    try {
      Producer<Long, Employee> producer = createProducer();
      Employee bob = Employee.newBuilder().setAge(35).setFirstName("Bob").setLastName("Jones").setFlName("PK")
          .setPhoneNumber(PhoneNumber.newBuilder().setAreaCode("301").setCountryCode("1").setPrefix("555").setNumber("1234").build()).build();
      IntStream.range(43, 44).forEach(index -> {
        producer.send(new ProducerRecord<>(TOPIC, 1L * index, bob));
      });
      producer.flush();
      producer.close();
    } catch (final Exception ex) {
       ex.printStackTrace();
    }
  }
}
