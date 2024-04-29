package com.ppalma.kafkaproducer.controller;

import com.ppalma.kafkaproducer.model.Student;
import com.ppalma.kafkaproducer.service.StudentMsgSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students/messages")
public class StudentMsgController {

  private final StudentMsgSenderService studentMsgSenderService;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void sendMessage(@RequestBody Student student) {
    this.studentMsgSenderService.sendStudentMsgToTopic(student);
  }
}
