package com.sofka.reto.application.usecases.teamusecases;

import com.sofka.reto.application.repository.TeamRepository;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import com.sofka.reto.infraestructure.rest.mapper.TeamMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

    public Mono<TeamDTO> getById(String id) {
        return teamRepository
                .findById(Integer.parseInt(id))
                .map(teamMapper.teamToTeamDTO());
    }

    public Mono<TeamDTO> getByName(String name) {
        return teamRepository.findByName(name)
                .map(teamMapper.teamToTeamDTO());
    }

    public Mono<TeamDTO> getByCode(String code) {
        return teamRepository.findByCode(code)
                .map(teamMapper.teamToTeamDTO());
    }

    public Flux<TeamDTO> getByCountry(String country) {
        return teamRepository.findByCountry(country)
                .map(teamMapper.teamToTeamDTO());
    }
}
