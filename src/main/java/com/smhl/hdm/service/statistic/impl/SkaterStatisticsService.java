package com.smhl.hdm.service.statistic.impl;

import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.season.impl.SkaterSeason;
import com.smhl.hdm.models.nonentities.Statistic;
import com.smhl.hdm.service.participant.impl.SkaterService;
import com.smhl.hdm.service.statistic.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the statistics service class for skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SkaterStatisticsService implements StatisticsService<Skater> {

    private SkaterService skaterService;

    @Autowired
    public SkaterStatisticsService(SkaterService skaterService) {
        this.skaterService = skaterService;
    }


    @Override
    public Statistic<Skater> calculate(Skater entity) {

        if (this.skaterService.find(entity.getId()).isEmpty()) {
            return new Statistic<>();
        }

        Map<String, Double> stats = new HashMap<>();

        Double d = entity.getSeasons()
                .stream()
                .mapToInt(SkaterSeason::getGamesPlayed)
                .sum() * 1.0;

        stats.put("careerGP", d);

        return new Statistic<>(entity, stats);
    }
}
