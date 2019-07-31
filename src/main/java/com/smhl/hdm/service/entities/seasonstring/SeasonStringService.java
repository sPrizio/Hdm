package com.smhl.hdm.service.entities.seasonstring;

import com.google.common.collect.Lists;
import com.smhl.hdm.models.entities.seasonstring.SeasonString;
import com.smhl.hdm.repositories.seasonstring.SeasonStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service-layer for SeasonString
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SeasonStringService {

    private SeasonStringRepository seasonStringRepository;

    @Autowired
    public SeasonStringService(SeasonStringRepository seasonStringRepository) {
        this.seasonStringRepository = seasonStringRepository;
    }


    //  METHODS

    /**
     * Finds and returns all season strings in the system
     *
     * @return list of season strings
     */
    public List<SeasonString> getAllSeasonStrings() {
        return Lists.newArrayList(this.seasonStringRepository.findAll());
    }

    /**
     * Saves a season string to the db
     *
     * @param seasonString season string that we're creating or modifying
     * @return newly saved season string
     */
    public SeasonString save(SeasonString seasonString) {
        return this.seasonStringRepository.save(seasonString);
    }
}
