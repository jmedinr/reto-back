package com.sofka.reto.application.usecases.teamusecases;

import com.sofka.reto.application.repository.CreateTeam;
import com.sofka.reto.application.repository.TeamRepository;
import com.sofka.reto.application.services.SequenceGeneratorService;
import com.sofka.reto.infraestructure.rest.dto.TeamDTO;
import com.sofka.reto.infraestructure.rest.mapper.TeamMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import static com.sofka.reto.domain.collections.Team.SEQUENCE_TEAM;

@Service
@Validated
public class CreateTeamUseCase implements CreateTeam {

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    private final SequenceGeneratorService sequenceService;

    public CreateTeamUseCase(TeamRepository teamRepository, TeamMapper teamMapper, SequenceGeneratorService sequenceService) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.sequenceService = sequenceService;
    }


    @Override
    public Mono<TeamDTO> appy(TeamDTO teamDTO) {
        return sequenceService.getSequenceNumber(SEQUENCE_TEAM)
                .flatMap(id -> {
                    teamDTO.setId(id.intValue());
                    return teamRepository
                            .save(teamMapper.teamDTOToTeam(teamDTO).apply(teamDTO))
                            .thenReturn(teamDTO);
                });
    }
}
