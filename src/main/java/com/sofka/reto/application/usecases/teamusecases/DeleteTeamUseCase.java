package com.sofka.reto.application.usecases.teamusecases;

import com.sofka.reto.application.repository.TeamRepository;
import com.sofka.reto.infraestructure.rest.mapper.TeamMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteTeamUseCase implements Function<String, Mono<Void>> {

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    public DeleteTeamUseCase(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "The project id is required");
        return teamRepository.deleteById(Integer.parseInt(id));
    }
}
