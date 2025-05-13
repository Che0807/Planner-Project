package com.example.plannerproject.repository;

import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;

import java.util.List;

public interface PlannerRepository {

    PlannerResponseDto savePlanner(Planner planner);

    List<PlannerResponseDto> findAllPlanners();

    Planner findPlannerById(long id);

    void deletePlannerById(long id);

}
