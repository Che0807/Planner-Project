package com.example.plannerproject.dto;


import com.example.plannerproject.entity.Planner;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class PlannerResponseDto {

    private Long id;
    private String user;
    private String task;
    private LocalDate created;
    private LocalDate updated;

    public PlannerResponseDto(Planner planner) {
        this.id = planner.getId();
        this.user = planner.getUser();
        this.task = planner.getTask();
        this.created = planner.getCreated();
        this.updated = planner.getUpdated();
    }
}
