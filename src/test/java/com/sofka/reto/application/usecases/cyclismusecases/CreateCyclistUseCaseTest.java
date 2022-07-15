package com.sofka.reto.application.usecases.cyclismusecases;

import com.sofka.reto.application.repository.CyclistRepository;
import com.sofka.reto.application.services.SequenceGeneratorService;
import com.sofka.reto.domain.collections.Cyclist;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import com.sofka.reto.infraestructure.rest.mapper.CyclistMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CreateCyclistUseCaseTest {

    @Mock
    CyclistRepository cyclistRepository;

    @Mock
    CreateCyclistUseCase createCyclistUseCase;


    CyclistMapper cyclistMapper = new CyclistMapper();

    @Mock
    SequenceGeneratorService sequenceGeneratorService;

    @BeforeEach
    public void setup() {
        sequenceGeneratorService = Mockito.mock(SequenceGeneratorService.class);
        cyclistRepository = Mockito.mock(CyclistRepository.class);
        createCyclistUseCase = new CreateCyclistUseCase(cyclistRepository, cyclistMapper, sequenceGeneratorService);
    }

    @Test
    void validationCreateCyclistTest() {
        Cyclist cyclist = new Cyclist(1, "jose", "15", "jumbo", "chileno");
        CyclistDTO cyclistDTO = new CyclistDTO(1, "jose", "15", "jumbo", "chileno");

        Mockito.when(sequenceGeneratorService.getSequenceNumber("cyclist_sequence")).thenReturn(Mono.just(1));
        Mockito.when(cyclistRepository.save(Mockito.any(Cyclist.class))).thenReturn(Mono.just(cyclist));


        StepVerifier.create(createCyclistUseCase.apply(cyclistDTO))
                .expectNext(cyclistDTO)
                .verifyComplete();

        verify(sequenceGeneratorService).getSequenceNumber("cyclist_sequence");
    }

}