package com.example.plannerproject.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Planner {
    private Long id;
    private String user;
    private String task;
    private String password;
    private LocalDate created;
    private LocalDate updated;

    // 패스워드를 포함하지 않는 생성자
    public Planner(String user, String task) {
        this.user = user;
        this.task = task;
    }

    // 패스워드를 포함한 생성자
    public Planner(String user, String task, String password) {
        this.user = user;
        this.task = task;
        this.password = password;
    }
}

