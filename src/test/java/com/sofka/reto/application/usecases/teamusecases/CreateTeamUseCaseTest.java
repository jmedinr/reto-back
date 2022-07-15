package com.sofka.reto.application.usecases.teamusecases;

import com.sofka.reto.application.repository.TeamRepository;
import com.sofka.reto.application.services.SequenceGeneratorService;
import com.sofka.reto.domain.collections.Cyclist;
import com.sofka.reto.domain.collections.Team;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import com.sofka.reto.infraestructure.rest.mapper.TeamMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreateTeamUseCaseTest {

    @Mock
    TeamRepository teamRepository;

    @Mock
    CreateTeamUseCase createTeamUseCase;

    TeamMapper teamMapper = new TeamMapper();

    @Mock
    SequenceGeneratorService sequenceGeneratorService;

    @BeforeEach
    public void setup() {
        sequenceGeneratorService = Mockito.mock(SequenceGeneratorService.class);
        teamRepository = Mockito.mock(TeamRepository.class);
        createTeamUseCase = new CreateTeamUseCase(teamRepository, teamMapper, sequenceGeneratorService);
    }

    @Test
    void validationCreateTeamTest() {
        Cyclist cyclist = new Cyclist(1, "jose", "15", "jumbo", "chileno");
        CyclistDTO cyclistDTO = new CyclistDTO(1, "jose", "15", "jumbo", "chileno");
        Team team = new Team();
        team.setId(1);
        team.setName("ineos");
        team.setCountry("francia");
        team.setCode("1c");
        List<Cyclist> l1 = new ArrayList<>();
        l1.add(cyclist);
        team.setCyclists(l1);

        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setCountry(team.getCountry());
        teamDTO.setCode(team.getCode());
        teamDTO.setCyclists(team.getCyclists());

        Mockito.when(sequenceGeneratorService.getSequenceNumber("team_sequence")).thenReturn(Mono.just(1));
        Mockito.when(teamRepository.save(Mockito.any(Team.class))).thenReturn(Mono.just(team));

        StepVerifier.create(createTeamUseCase.appy(teamDTO))
                .expectNext(teamDTO)
                .verifyComplete();

        verify(sequenceGeneratorService).getSequenceNumber("team_sequence");

    }
}