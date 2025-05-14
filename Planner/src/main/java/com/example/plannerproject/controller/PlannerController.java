package com.example.plannerproject.controller;

import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;
import com.example.plannerproject.service.PlannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PlannerResponseDto> createPlanner(@RequestBody PlannerRequestDto plannerRequestDto) {

        return new ResponseEntity<>(plannerService.savePlanner(plannerRequestDto), HttpStatus.CREATED);
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
    public ResponseEntity<Planner> deletePlanner(@PathVariable Long id, @RequestBody PlannerRequestDto plannerRequestDto) {

        plannerService.deletePlanner(id,plannerRequestDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}


