package com.example.plannerproject.repository;

import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;

import java.util.List;
import java.util.Optional;

public interface PlannerRepository {

    PlannerResponseDto savePlanner(Planner planner);

    List<PlannerResponseDto> findAllPlanners();

    Optional<Planner> findPlannerById(long id);

    int deletePlanner(long id, PlannerRequestDto plannerRequestDto);

}
