package com.sofka.reto.application.usecases.cyclismusecases;

import com.sofka.reto.application.repository.CreateCyclist;
import com.sofka.reto.application.repository.CyclistRepository;
import com.sofka.reto.application.services.SequenceGeneratorService;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import com.sofka.reto.infraestructure.rest.mapper.CyclistMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import static com.sofka.reto.domain.collections.Team.SEQUENCE_TEAM;

@Service
@Validated
public class CreateCyclistUseCase implements CreateCyclist {

    private final CyclistRepository cyclistRepository;

    private final CyclistMapper cyclistMapper;

    private final SequenceGeneratorService sequenceService;

    public CreateCyclistUseCase(CyclistRepository cyclistRepository, CyclistMapper cyclistMapper, SequenceGeneratorService sequenceService) {
        this.cyclistRepository = cyclistRepository;
        this.cyclistMapper = cyclistMapper;
        this.sequenceService = sequenceService;
    }


    @Override
    public Mono<CyclistDTO> apply(CyclistDTO cyclistDTO) {
        return sequenceService.getSequenceNumber(SEQUENCE_TEAM)
                .flatMap(id -> {
                    cyclistDTO.setId(id.intValue());
                    return cyclistRepository
                            .save(cyclistMapper.cyclistDTOToCyclist(cyclistDTO).apply(cyclistDTO))
                            .thenReturn(cyclistDTO);
                });
    }
}
