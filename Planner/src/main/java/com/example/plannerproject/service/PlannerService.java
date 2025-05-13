package com.example.plannerproject.service;


import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;

public interface PlannerService {

    PlannerResponseDto savePlanner(PlannerRequestDto plannerRequestDto);
}
