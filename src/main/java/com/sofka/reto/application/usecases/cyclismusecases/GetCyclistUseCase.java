package com.sofka.reto.application.usecases.cyclismusecases;

import com.sofka.reto.application.repository.CyclistRepository;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import com.sofka.reto.infraestructure.rest.mapper.CyclistMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Service
@Validated
public class GetCyclistUseCase implements Supplier<Flux<CyclistDTO>> {

    private final CyclistRepository cyclistRepository;

    private final CyclistMapper cyclistMapper;

    public GetCyclistUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Flux<CyclistDTO> get() {
        return cyclistRepository.findAll()
                .map(cyclistMapper.cyclistToCyclistDTO());
    }

    public Mono<CyclistDTO> getById(String id) {
        return cyclistRepository
                .findById(Integer.parseInt(id))
                .map(cyclistMapper.cyclistToCyclistDTO());
    }

    public Mono<CyclistDTO> getByCyclistNumber(String CyclistNumber) {
        return cyclistRepository.findByCyclistNumber(CyclistNumber)
                .map(cyclistMapper.cyclistToCyclistDTO());
    }

    public Flux<CyclistDTO> getByCyclistTeam(String team) {
        return cyclistRepository.findByCyclistTeam(team)
                .map(cyclistMapper.cyclistToCyclistDTO());
    }

    public Flux<CyclistDTO> getByCyclistNationality(String nationality) {
        return cyclistRepository.findByCyclistNationality(nationality)
                .map(cyclistMapper.cyclistToCyclistDTO());
    }
}
