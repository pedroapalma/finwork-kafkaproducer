package com.ppalma.kafkaproducer.service;

import com.ppalma.kafkaproducer.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class StudentMsgSenderService {

  private final KafkaTemplate<String, Student> kafkaTemplate;

  private final String topic;

  public StudentMsgSenderService(KafkaTemplate<String, Student> kafkaTemplate,
      @Value("${kafka.topic.name}") String topic) {
    this.kafkaTemplate = kafkaTemplate;
    this.topic = topic;
  }

  public void sendStudentMsgToTopic(Student student) {
    this.kafkaTemplate.send(this.topic, student);
  }

}
