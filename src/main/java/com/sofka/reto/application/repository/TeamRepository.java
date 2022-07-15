package com.sofka.reto.application.repository;

import com.sofka.reto.domain.collections.Team;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @repository this implements a repository interface for Teams
 * @author juan.medina@sofka.com.co
 */
public interface TeamRepository extends ReactiveCrudRepository<Team, Integer> {

    Mono<Team> findByName(String name);

    Mono<Team> findByCode(String code);

    Flux<Team> findByCountry(String country);
}
