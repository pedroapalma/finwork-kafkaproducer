package com.ppalma.kafkaproducer.config;

import com.ppalma.kafkaproducer.model.Student;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;

  private Map<String, Object> producerConfig() {
    Map<String, Object> properties = new HashMap<>();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    properties.put(ProducerConfig.ACKS_CONFIG, "all");
    properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
    return properties;
  }

  @Bean
  public ProducerFactory<String, Student> producerFactory() {
    return new DefaultKafkaProducerFactory<>(this.producerConfig(),
        new StringSerializer(),
        new JsonSerializer<Student>()
            .noTypeInfo());
  }

  @Bean
  public KafkaTemplate<String, Student> kafkaTemplate(
      ProducerFactory<String, Student> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }
}
