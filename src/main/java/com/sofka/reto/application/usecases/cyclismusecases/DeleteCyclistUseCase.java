package com.sofka.reto.application.usecases.cyclismusecases;

import com.sofka.reto.application.repository.CyclistRepository;
import com.sofka.reto.domain.collections.Cyclist;
import com.sofka.reto.infraestructure.rest.mapper.CyclistMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class DeleteCyclistUseCase implements Function<String, Mono<Void>> {

    private final CyclistRepository cyclistRepository;

    private final CyclistMapper cyclistMapper;

    public DeleteCyclistUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }


    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "The project id is required");
        return cyclistRepository.deleteById(Integer.parseInt(id));
    }
}
