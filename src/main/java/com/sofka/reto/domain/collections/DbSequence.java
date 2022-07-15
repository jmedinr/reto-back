package com.sofka.reto.domain.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Configuration Configuration template for id integers generator sequence
 * @author juan.medina@sofka.com.co
 * @version 1.0
 */
@Document(collection = "db_sequence")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbSequence {
    @Id
    private String id;
    private int seq;

}