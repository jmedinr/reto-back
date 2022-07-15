package com.sofka.reto.infraestructure.rest.dto;

import com.sofka.reto.domain.collections.Cyclist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class TeamDTO {

    private Integer id;

    @Length(min = 3, max = 50, message = "name length must be between 3 and 50 characters")
    @NotBlank(message = "name is required")
    private String name;

    @Length(min = 1, max = 3, message = "name length must be between 1 and 3 characters")
    @NotBlank(message = "code is required")
    private String code;

    @Length(min = 3, max = 60, message = "name length must be between 3 and 60 characters")
    @NotBlank(message = "country is required")
    private String country;

    @Length(min = 1, max = 8, message = "name length must be between 1 and 8 cyclists")
    private List<Cyclist> cyclists;

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country='" + country + '\'' +
                ", cyclists=" + cyclists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDTO teamDTO = (TeamDTO) o;
        return Objects.equals(id, teamDTO.id) && Objects.equals(name, teamDTO.name) && Objects.equals(code, teamDTO.code) && Objects.equals(country, teamDTO.country) && Objects.equals(cyclists, teamDTO.cyclists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, country, cyclists);
    }
}
