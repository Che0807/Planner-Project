package com.example.plannerproject.service;


import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;
import com.example.plannerproject.repository.PlannerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlannerServiceImpl implements PlannerService {

    private final PlannerRepository plannerRepository;

    public PlannerServiceImpl(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    @Override
    public PlannerResponseDto savePlanner(PlannerRequestDto dto) {

        Planner planner = new Planner(dto.getTask(), dto.getUser(),dto.getPassword());


        return plannerRepository.savePlanner(planner);
    }
}
