package com.smhl.hdm.service.participant.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.models.details.participant.SkaterGameDetails;
import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.models.season.impl.SkaterSeason;
import com.smhl.hdm.repositories.participant.SkaterRepository;
import com.smhl.hdm.service.participant.ParticipantService;
import com.smhl.hdm.service.season.impl.SkaterSeasonService;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation for the skater service class
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SkaterService implements ParticipantService<Skater, SkaterGameDetails> {

    private SkaterRepository skaterRepository;
    private SkaterSeasonService skaterSeasonService;

    @Autowired
    public SkaterService(SkaterRepository skaterRepository, SkaterSeasonService skaterSeasonService) {
        this.skaterRepository = skaterRepository;
        this.skaterSeasonService = skaterSeasonService;
    }

    @Override
    public void updateStats(SkaterGameDetails details) {

        SkaterSeason season = details.getSkater().getCurrentSeason();

        if (season != null) {
            season.incrementGamesPlayed();
            season.incrementGoals(details.getGoals());
            season.incrementAssists(details.getAssists());
            season.incrementShots(details.getShots());
            season.incrementBlockedShots(details.getBlockedShots());

            this.skaterSeasonService.save(season);
        }
    }

    @Override
    public List<Skater> getAllActiveParticipants(String field, String order) {
        return this.skaterRepository.findByActiveSorted(true, HdmUtils.getCurrentSeasonString(), field, order);
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
}
