package com.ppalma.kafkaproducer.controller;

import com.ppalma.kafkaproducer.model.Student;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/students")
public class StudentsController {

  private static int i = 0;

  @Value("${value:default}")
  private String value;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Student> getStudents() {
    log.info("Request In {}", i++);

    return List.of(
        Student.builder()
            .dni("123456")
            .name(this.value)
            .notes(List.of(1D, 2D, 3D))
            .averageNotes(2D)
            .build());
  }
}
