package com.sofka.reto.application.repository;

import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @Repository implementation of the repository interface for create a Cyclist
 * @author juan.medina@sofka.com.co
 * @Version 1.0
 */
public interface CreateCyclist {
    Mono<CyclistDTO> apply(@Valid CyclistDTO cyclistDTO);
}
