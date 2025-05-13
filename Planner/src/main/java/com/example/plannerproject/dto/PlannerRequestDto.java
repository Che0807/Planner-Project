package com.example.plannerproject.dto;

import com.example.plannerproject.entity.Planner;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PlannerRequestDto {
    private Long id;
    private String user;
    private String task;
    private String password;
}

