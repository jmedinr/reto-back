package com.sofka.reto.application.usecases.teamusecases;

import com.sofka.reto.application.repository.TeamRepository;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import com.sofka.reto.infraestructure.rest.mapper.TeamMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetTeamUseCase implements Supplier<Flux<TeamDTO>> {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public GetTeamUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Flux<TeamDTO> get() {
        return teamRepository.findAll()
                .map(teamMapper.teamToTeamDTO());
    }
}
