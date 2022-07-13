package com.sofka.reto.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CyclistDTO {

    private Integer id;

    @Length(min = 3, max = 50, message = "name length must be between 3 and 50 characters")
    @NotBlank(message = "name is required")
    private String name;

    @Length(min = 1, max = 3, message = "description length must be between 1 and 3 characters")
    @NotBlank(message = "cyclistNumber is required")
    private String cyclistNumber;

    @Length(min = 3, max = 50, message = "comment length must be between 1 and 50 characters")
    @NotBlank(message = "team is required")
    private String team;

    @Length(min = 3, max = 50, message = "comments length must be between 3 and 50 characters")
    @NotBlank(message = "nationality is required")
    private String nationality;

    @Override
    public String toString() {
        return "CyclistDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cyclistNumber='" + cyclistNumber + '\'' +
                ", team='" + team + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CyclistDTO that = (CyclistDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(cyclistNumber, that.cyclistNumber) && Objects.equals(team, that.team) && Objects.equals(nationality, that.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cyclistNumber, team, nationality);
    }
}
