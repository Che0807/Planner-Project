package com.example.plannerproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlannerRequestDto {

    private Long id;
    private String user;
    private String task;
    private String password;

    public PlannerRequestDto(String password) {
        this.password = password;
    }
}


