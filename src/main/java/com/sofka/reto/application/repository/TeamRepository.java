package com.sofka.reto.application.repository;

import com.sofka.reto.domain.collections.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TeamRepository extends ReactiveCrudRepository<Team, Integer>{

    Mono<Team> findAllByName(String name);
    Mono<Team> findAllByCode(String code);
    Flux<Team> findByCountry(String country);
}
