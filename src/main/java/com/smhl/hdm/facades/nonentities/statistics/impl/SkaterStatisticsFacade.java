package com.smhl.hdm.facades.nonentities.statistics.impl;

import com.smhl.hdm.facades.nonentities.statistics.StatisticsFacade;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.nonentities.Statistic;
import com.smhl.hdm.service.entities.participant.impl.SkaterService;
import com.smhl.hdm.service.nonentities.statistic.impl.SkaterStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Obtains statistics for skaters for use in controllers
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SkaterStatisticsFacade implements StatisticsFacade {

    private SkaterService skaterService;
    private SkaterStatisticsService skaterStatisticsService;

    @Autowired
    public SkaterStatisticsFacade(SkaterService skaterService, SkaterStatisticsService skaterStatisticsService) {
        this.skaterService = skaterService;
        this.skaterStatisticsService = skaterStatisticsService;
    }

    @Override
    public Statistic obtainStatistics(Long id) {
        Optional<Skater> skater = this.skaterService.find(id);
        return skater.isPresent() ? this.skaterStatisticsService.calculate(skater.get()) : new Statistic();
    }

    @Override
    public Statistic obtainActiveStatistics(String seasonString) {
        return this.skaterStatisticsService.calculate(this.skaterService.getAllParticipantsForSeason(seasonString, "points", "desc"), seasonString);
    }
}
