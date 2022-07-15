package com.sofka.reto.application.usecases.teamusecases;

import com.sofka.reto.application.repository.TeamRepository;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import com.sofka.reto.infraestructure.rest.mapper.TeamMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class GetByCodeTeamUseCase implements Function<String, Mono<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public GetByCodeTeamUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Mono<TeamDTO> apply(String code) {
        return teamRepository.findByCode(code)
                .map(teamMapper.teamToTeamDTO());
    }
}
