package com.sofka.reto.application.repository;

import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
public interface CreateTeam {
    Mono<TeamDTO> appy (@Valid TeamDTO teamDTO);
}
