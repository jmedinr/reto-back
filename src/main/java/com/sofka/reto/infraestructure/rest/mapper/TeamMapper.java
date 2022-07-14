package com.sofka.reto.infraestructure.rest.mapper;

import com.sofka.reto.domain.collections.Team;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TeamMapper {

    public Function<TeamDTO, Team> teamDTOToTeam(Object o) {
        return teamDTO -> {
            Team team = new Team();
            team.setId(teamDTO.getId());
            team.setName(teamDTO.getName());
            team.setCode(teamDTO.getCode());
            team.setCountry(teamDTO.getCountry());
            team.setCyclists(teamDTO.getCyclists());
            return team;
        };
    }

    public Function<Team, TeamDTO> teamToTeamDTO() {
        return team -> new TeamDTO(
                team.getId(),
                team.getName(),
                team.getCode(),
                team.getCountry(),
                team.getCyclists()
        );
    }
}
