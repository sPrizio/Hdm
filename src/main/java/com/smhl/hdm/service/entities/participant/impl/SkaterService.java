package com.smhl.hdm.service.entities.participant.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.season.impl.SkaterSeason;
import com.smhl.hdm.repositories.participant.skater.SkaterRepository;
import com.smhl.hdm.service.entities.participant.ParticipantService;
import com.smhl.hdm.service.entities.season.impl.SkaterSeasonService;
import com.smhl.hdm.translators.participant.SkaterTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation for the skater service class. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SkaterService implements ParticipantService<Skater, SkaterGameDetails> {

    private SkaterRepository skaterRepository;
    private SkaterSeasonService skaterSeasonService;
    private SkaterTranslator skaterTranslator;

    @Autowired
    public SkaterService(SkaterRepository skaterRepository, SkaterSeasonService skaterSeasonService, SkaterTranslator skaterTranslator) {
        this.skaterRepository = skaterRepository;
        this.skaterSeasonService = skaterSeasonService;
        this.skaterTranslator = skaterTranslator;
    }


    //  METHODS

    @Override
    public void updateStats(SkaterGameDetails details) {

        String seasonString = HdmUtils.getSeasonStringForLocalDateTime(details.getGameTime());
        SkaterSeason season = details.getParticipant().getSeasonForSeasonString(seasonString);

        if (season == null) {
            season = new SkaterSeason();
            season.setSeasonString(seasonString);

            Skater skater = details.getParticipant();
            skater.addSeason(season);

            this.skaterRepository.save(skater);
        }

        season.incrementGamesPlayed();
        season.incrementGoals(details.getGoals());
        season.incrementAssists(details.getAssists());
        season.incrementShots(details.getShots());
        season.incrementBlockedShots(details.getBlockedShots());

        this.skaterSeasonService.save(season);
    }

    @Override
    public List<Skater> getAllParticipantsForSeason(String seasonString, String field, String order) {
        return this.skaterRepository.findBySeasonStringSorted(seasonString, field, order);
    }

    @Override
    public void refresh(Skater entity) {
        this.skaterRepository.refresh(entity);
    }

    @Override
    public Optional<Skater> find(Long id) {
        return this.skaterRepository.findById(id);
    }

    @Override
    public List<Skater> findAll() {
        return Lists.newArrayList(this.skaterRepository.findAll());
    }

    @Override
    public Skater save(Skater entity) {
        return this.skaterRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

        if (find(id).isPresent()) {
            this.skaterRepository.deleteById(id);
        }
    }

    @Override
    public Skater create(Map<String, Object> params) {

        Skater skater = this.skaterTranslator.translate(params);

        if (skater != null) {
            return this.skaterRepository.save(skater);
        }

        return null;
    }

    /**
     * Returns the top skaters for a given stat category and limits the number of results
     *
     * @param stat  field on which we want to judge skaters
     * @param limit number of results to return
     * @return sorted limited list based on a stat
     */
    public List<Skater> getTopSkatersForStatAndLimit(String stat, Integer limit) {
        return this.skaterRepository.findTopSkatersForStatAndLimit(stat, limit);
    }
}
