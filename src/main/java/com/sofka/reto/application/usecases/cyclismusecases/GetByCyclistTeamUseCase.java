package com.sofka.reto.application.usecases.cyclismusecases;

import com.sofka.reto.application.repository.CyclistRepository;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import com.sofka.reto.infraestructure.rest.mapper.CyclistMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
@Validated
public class GetByCyclistTeamUseCase implements Function<String, Flux<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;

    private final CyclistMapper cyclistMapper;

    public GetByCyclistTeamUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Flux<CyclistDTO> apply(String team) {
        return cyclistRepository.findByCyclistTeam(team)
                .map(cyclistMapper.cyclistToCyclistDTO());
    }
}