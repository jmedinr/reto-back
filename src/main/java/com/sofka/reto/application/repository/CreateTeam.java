package com.sofka.reto.application.repository;

import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @Repository  implementation  of interface fot create a Team
 *  @author juan.medina@sofka.com.co
 * @Version 1.0
 */
public interface CreateTeam {
    Mono<TeamDTO> appy(@Valid TeamDTO teamDTO);
}
