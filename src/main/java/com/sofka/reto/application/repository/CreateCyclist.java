package com.sofka.reto.application.repository;

import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface CreateCyclist {
    Mono<CyclistDTO> apply(@Valid CyclistDTO cyclistDTO);
}
