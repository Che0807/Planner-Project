package com.example.plannerproject.service;


import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;
import com.example.plannerproject.repository.PlannerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlannerServiceImpl implements PlannerService {

    private final PlannerRepository plannerRepository;

    public PlannerServiceImpl(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    @Override
    public PlannerResponseDto savePlanner(PlannerRequestDto dto) {

        Planner planner = new Planner(dto.getUser(), dto.getTask(), dto.getPassword());

        return plannerRepository.savePlanner(planner);
    }

    @Override
    public List<PlannerResponseDto> findAllPlanners() {
        return plannerRepository.findAllPlanners();
    }

    @Override
    public PlannerResponseDto findPlannerById(Long id) {
        Optional<Planner> optionalPlanner = plannerRepository.findPlannerById(id);

        if (optionalPlanner.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 값입니다.");
        }

        return new PlannerResponseDto(optionalPlanner.get());
    }
}
