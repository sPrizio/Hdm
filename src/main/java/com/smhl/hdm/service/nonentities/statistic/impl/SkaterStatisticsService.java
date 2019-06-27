package com.smhl.hdm.service.nonentities.statistic.impl;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.season.impl.SkaterSeason;
import com.smhl.hdm.models.nonentities.Statistic;
import com.smhl.hdm.service.nonentities.statistic.StatisticsService;
import com.smhl.hdm.utils.StatisticsUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the statistics service class for skaters. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SkaterStatisticsService implements StatisticsService<Skater> {

    @Override
    public Statistic calculate(Skater entity) {

        Map<String, DoubleSummaryStatistics> statisticsMap = new HashMap<>();

        CoreConstants.SKATER_SEASON_COLLECTORS.forEach(
                (attribute, collector) -> statisticsMap.put(attribute, new StatisticsUtils<SkaterSeason>().calculateStatistics(attribute, collector, entity.getSeasons()))
        );

        return new Statistic(statisticsMap);
    }

    @Override
    public Statistic calculate(List<Skater> entities, String seasonString) {

        Map<String, DoubleSummaryStatistics> statisticsMap = new HashMap<>();
        List<SkaterSeason> seasons =
                entities
                        .stream()
                        .map(
                                skater ->
                                        skater.getSeasonForSeasonString(seasonString))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        CoreConstants.SKATER_SEASON_COLLECTORS.forEach(
                (attribute, collector) -> statisticsMap.put(attribute, new StatisticsUtils<SkaterSeason>().calculateStatistics(attribute, collector, seasons))
        );

        return new Statistic(statisticsMap);
    }
}
