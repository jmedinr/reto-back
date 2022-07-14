package com.sofka.reto.application.repository;

import com.sofka.reto.domain.collections.Cyclist;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CyclistRepository extends ReactiveCrudRepository<Cyclist, Integer> {

    Flux<Cyclist> findByName(String name);

    Mono<Cyclist> findByCyclistNumber(String cyclistNumber);

    Flux<Cyclist> findByTeam(String team);

    Flux<Cyclist> findByNationality(String nationality);
}
