package com.sofka.reto.application.usecases.cyclismusecases;

import com.sofka.reto.application.repository.CreateCyclist;
import com.sofka.reto.application.repository.CyclistRepository;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import com.sofka.reto.infraestructure.rest.mapper.CyclistMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateCyclistUseCase implements CreateCyclist {

    private final CyclistRepository cyclistRepository;

    private final CyclistMapper cyclistMapper;

    public UpdateCyclistUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
    }

    @Override
    public Mono<CyclistDTO> apply(CyclistDTO cyclistDTO) {
        Objects.requireNonNull(cyclistDTO.getId(), "Id of the question is required");
        return cyclistRepository
                .save(cyclistMapper.cyclistDTOToCyclist(cyclistDTO).apply(cyclistDTO))
                .thenReturn(cyclistDTO);
    }
}
