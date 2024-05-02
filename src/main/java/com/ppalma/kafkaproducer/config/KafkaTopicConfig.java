package com.ppalma.kafkaproducer.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

  @Value("${kafka.topic.name}")
  private String topic;

  @Bean
  public NewTopic generateTopic() {

    Map<String, String> configurations = new HashMap<>();
    configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // 1 Day
    configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // 1GB
    configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000000");

    return TopicBuilder.name(this.topic)
        .partitions(3)
        .replicas(1)
        .configs(configurations)
        .build();
  }
}
