package com.example.plannerproject.service;


import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;

import java.util.List;
import java.util.Optional;

public interface PlannerService {

    PlannerResponseDto savePlanner(PlannerRequestDto plannerRequestDto);

    List<PlannerResponseDto> findAllPlanners();

    PlannerResponseDto findPlannerById(Long id);
}
