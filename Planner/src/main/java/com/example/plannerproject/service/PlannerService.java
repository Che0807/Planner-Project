package com.example.plannerproject.service;


import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;

import java.util.List;

public interface PlannerService {

    PlannerResponseDto savePlanner(PlannerRequestDto plannerRequestDto);

    List<PlannerResponseDto> findAllPlanners();

    PlannerResponseDto findPlannerById(Long id);

    PlannerResponseDto updatePlanner(Long id, PlannerRequestDto plannerRequestDto);

    void deletePlanner(Long id, PlannerRequestDto plannerRequestDto);
}
