package com.example.plannerproject.repository;

import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplatePlannerRepository implements PlannerRepository {

    private final JdbcTemplate jdbctemplate;

    public JdbcTemplatePlannerRepository(DataSource dataSource) {
        this.jdbctemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PlannerResponseDto savePlanner(Planner planner) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbctemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id").usingColumns("user","task","password");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("user", planner.getUser());
        parameters.put("task", planner.getTask());
        parameters.put("password", planner.getPassword());

      Number key =  jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        LocalDate currentDate = LocalDate.now();

        return new PlannerResponseDto(key.longValue(), planner.getUser(), planner.getTask(), currentDate, currentDate );
    }

    @Override
    public List<PlannerResponseDto> findAllPlanners() {
        return List.of();
    }

    @Override
    public Planner findPlannerById(long id) {
        return null;
    }

    @Override
    public void deletePlannerById(long id) {

    }
}


