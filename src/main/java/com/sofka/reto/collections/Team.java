package com.sofka.reto.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "team")
public class Team {

    @Transient
    public static final String SEQUENCE_TEAM = "team_sequence";

    @Id
    private Integer id;

    private String name;

    private String code;

    private String country;

    public Team() {
    }

    public Team(Integer id, String name, String code, String country) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
