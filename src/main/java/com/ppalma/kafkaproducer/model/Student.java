package com.ppalma.kafkaproducer.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

  private String dni;

  private String name;

  private List<Double> notes;

  private Double averageNotes;
}
