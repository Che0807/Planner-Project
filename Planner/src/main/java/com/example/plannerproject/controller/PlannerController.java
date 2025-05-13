package com.example.plannerproject.controller;

import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;
import com.example.plannerproject.service.PlannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<PlannerResponseDto> findAllPlanners() {

        return plannerService.findAllPlanners();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlannerResponseDto> findPlannerById(@PathVariable Long id) {
        PlannerResponseDto responseDto = plannerService.findPlannerById(id);
        return new ResponseEntity<>(plannerService.findPlannerById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Planner> deletePlanner(@PathVariable Long id) {

        plannerService.deletePlanner(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}


