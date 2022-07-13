package com.sofka.reto.infraestructure.rest.mapper;

import com.sofka.reto.domain.collections.Cyclist;
import com.sofka.reto.infraestructure.rest.dto.CyclistDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CyclistMapper {

    public Function<CyclistDTO, Cyclist> cyclistDTOToCyclist() {
        return cyclistDTO -> {
            Cyclist cyclist = new Cyclist();
            cyclist.setId(cyclistDTO.getId());
            cyclist.setName(cyclistDTO.getName());
            cyclist.setCyclistNumber(cyclistDTO.getCyclistNumber());
            cyclist.setTeam(cyclistDTO.getTeam());
            cyclist.setNationality(cyclistDTO.getNationality());
            return cyclist;
        };
    }

    public Function<Cyclist, CyclistDTO> cyclistToCyclistDTO() {
        return cyclist -> new CyclistDTO(
                cyclist.getId(),
                cyclist.getName(),
                cyclist.getCyclistNumber(),
                cyclist.getTeam(),
                cyclist.getNationality()
        );
    }
}
