package com.smhl.hdm.commandlinerunners;

import com.smhl.hdm.models.entities.seasonstring.SeasonString;
import com.smhl.hdm.repositories.seasonstring.SeasonStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Loads SeasonString data into the database for testing
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
@Order(1)
public class SeasonStringDataLoader implements CommandLineRunner {

    private SeasonStringRepository seasonStringRepository;

    @Autowired
    public SeasonStringDataLoader(SeasonStringRepository seasonStringRepository) {
        this.seasonStringRepository = seasonStringRepository;
    }


    //  METHODS

    @Override
    public void run(String... args) {

        this.seasonStringRepository.deleteAll();

        this.seasonStringRepository.save(new SeasonString("2016-2017"));
        this.seasonStringRepository.save(new SeasonString("2017-2018"));
        this.seasonStringRepository.save(new SeasonString("2018-2019"));
    }
}
