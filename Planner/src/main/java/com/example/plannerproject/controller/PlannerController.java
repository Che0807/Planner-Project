package com.example.plannerproject.controller;

import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.service.PlannerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planners")
public class PlannerController {

    private PlannerService plannerService;

    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }

    @PostMapping
    public PlannerResponseDto createPlanner(@RequestBody PlannerRequestDto plannerRequestDto) {
        return plannerService.savePlanner(plannerRequestDto);
    }
}

