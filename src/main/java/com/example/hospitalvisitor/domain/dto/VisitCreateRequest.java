package com.example.hospitalvisitor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VisitCreateRequest {
    private Integer hospitalId;
    private String disease;
    private float amount;
}
