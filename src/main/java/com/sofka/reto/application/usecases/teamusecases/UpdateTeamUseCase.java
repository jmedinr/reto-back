package com.sofka.reto.application.usecases.teamusecases;

import com.sofka.reto.application.repository.CreateTeam;
import com.sofka.reto.application.repository.TeamRepository;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import com.sofka.reto.infraestructure.rest.mapper.TeamMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateTeamUseCase implements CreateTeam {

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    public UpdateTeamUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Mono<TeamDTO> appy(TeamDTO teamDTO) {
        Objects.requireNonNull(teamDTO.getId(), "Id of the question is required");
        return teamRepository
                .save(teamMapper.teamDTOToTeam(teamDTO).apply(teamDTO))
                .thenReturn(teamDTO);
    }
}
