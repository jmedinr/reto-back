package com.sofka.reto.application.services;

import com.sofka.reto.domain.collections.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGeneratorService {

    @Autowired
    private ReactiveMongoOperations reactiveMongoOperations;

    public Mono<Integer> getSequenceNumber(String sequenceName) {
        //get sequence no
        Query query = new Query(Criteria.where("id").is(sequenceName));
        //update the sequence no
        Update update = new Update().inc("seq", 1);
        //modify in document
        Mono<DbSequence> counter = reactiveMongoOperations
                .findAndModify(query,
                        update, options().returnNew(true).upsert(true),
                        DbSequence.class);

        return Objects.requireNonNull(counter.map(DbSequence::getSeq));
    }
}
