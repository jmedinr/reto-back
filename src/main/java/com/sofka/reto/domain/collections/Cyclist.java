package com.sofka.reto.domain.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cyclist")
public class Cyclist {

    @Transient
    public static final String SEQUENCE_CYCLIST = "cyclist_sequence";

    @Id
    private Integer id;

    private String name;

    private String cyclistNumber;

    private String team;

    private String nationality;

    public Cyclist() {
    }

    public Cyclist(Integer id, String name, String cyclistNumber, String team, String nationality) {
        this.id = id;
        this.name = name;
        this.cyclistNumber = cyclistNumber;
        this.team = team;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Cyclist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cyclistNumber='" + cyclistNumber + '\'' +
                ", team='" + team + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
