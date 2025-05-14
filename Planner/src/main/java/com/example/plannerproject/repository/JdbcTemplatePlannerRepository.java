package com.example.plannerproject.repository;

import com.example.plannerproject.dto.PlannerRequestDto;
import com.example.plannerproject.dto.PlannerResponseDto;
import com.example.plannerproject.entity.Planner;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplatePlannerRepository implements PlannerRepository {

    private final JdbcTemplate jdbctemplate;

    public JdbcTemplatePlannerRepository(DataSource dataSource) {
        this.jdbctemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PlannerResponseDto savePlanner(Planner planner) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbctemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id").usingColumns("user", "task", "password");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("user", planner.getUser());
        parameters.put("task", planner.getTask());
        parameters.put("password", planner.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        LocalDate currentDate = LocalDate.now();

        return new PlannerResponseDto(key.longValue(), planner.getUser(), planner.getTask(), currentDate, currentDate);
    }

    @Override
    public List<PlannerResponseDto> findAllPlanners() {

        return jdbctemplate.query("SELECT * FROM schedule", plannerRowMapper());
    }

    @Override
    public Optional<Planner> findPlannerById(long id) {
        List<Planner> result = jdbctemplate.query("SELECT * FROM schedule WHERE id = ? ORDER BY updated DESC", plannerRowMapperV2(), id);
        return result.stream().findAny();
    }

    @Override
    public int updatePlanner(Long id, PlannerRequestDto plannerRequestDto) {

        String sql = "SELECT password FROM schedule WHERE id = ?";
        String updatesql = "UPDATE schedule SET user = ?, task = ? WHERE id = ?";

        String dbPassword = jdbctemplate.queryForObject(sql, String.class, id);

        if (plannerRequestDto.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule with id " + id + " not found.");
        }

        if (!dbPassword.equals(plannerRequestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match.");
        }

        return jdbctemplate.update(updatesql, plannerRequestDto.getUser(), plannerRequestDto.getTask(), id);
    }


    @Override
    public int deletePlanner(long id, PlannerRequestDto plannerRequestDto) {
        String sql = "SELECT password FROM schedule WHERE id = ?";
        String deleteSql = "DELETE FROM schedule WHERE id = ?";

        String dbPassword = jdbctemplate.queryForObject(sql, String.class, id);

        if (dbPassword == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule with id " + id + " not found.");
        }

        if (!dbPassword.equals(plannerRequestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password does not match.");
        }

        // 비밀번호가 일치하면 삭제
        return jdbctemplate.update(deleteSql, id);
    }


    private RowMapper<PlannerResponseDto> plannerRowMapper() {

        return new RowMapper<PlannerResponseDto>() {
            @Override
            public PlannerResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new PlannerResponseDto(
                        rs.getLong("id"),
                        rs.getString("user"),
                        rs.getString("task"),
                        rs.getDate("created").toLocalDate(),
                        rs.getDate("updated").toLocalDate());
            }
        };
    }

    private RowMapper<Planner> plannerRowMapperV2() {
        return new RowMapper<Planner>() {
            @Override
            public Planner mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Planner(
                        rs.getLong("id"),
                        rs.getString("user"),
                        rs.getString("task"),
                        rs.getDate("created").toLocalDate(),
                        rs.getDate("updated").toLocalDate());
            }
        };
    }
}


