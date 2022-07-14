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
public class GetByCyclistNumberUseCase implements Function<String, Mono<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;

    private final CyclistMapper cyclistMapper;

    public GetByCyclistNumberUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Mono<CyclistDTO> apply(String cyclistNumber) {
        return cyclistRepository.findByCyclistNumber(cyclistNumber)
                .map(cyclistMapper.cyclistToCyclistDTO());
    }
}
