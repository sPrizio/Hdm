package com.smhl.hdm.service.statistic.impl;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.entities.season.impl.GoalieSeason;
import com.smhl.hdm.models.nonentities.Statistic;
import com.smhl.hdm.service.participant.impl.GoalieService;
import com.smhl.hdm.service.statistic.StatisticsService;
import com.smhl.hdm.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the statistics service for goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GoalieStatisticsService implements StatisticsService<Goalie> {

    private GoalieService goalieService;

    @Autowired
    public GoalieStatisticsService(GoalieService goalieService) {
        this.goalieService = goalieService;
    }


    @Override
    public Statistic calculate(Goalie entity) {

        if (this.goalieService.find(entity.getId()).isEmpty()) {
            return new Statistic();
        }

        Map<String, DoubleSummaryStatistics> statisticsMap = new HashMap<>();

        CoreConstants.GOALIE_SEASON_COLLECTORS.forEach(
                (attribute, collector) -> statisticsMap.put(attribute, new StatisticsUtils<GoalieSeason>().calculateStatistics(attribute, collector, entity.getSeasons()))
        );

        return new Statistic(statisticsMap);
    }

    @Override
    public Statistic calculate(List<Goalie> entities, String seasonString) {

        if (
                entities
                        .stream()
                        .anyMatch(goalie -> this.goalieService.find(goalie.getId()).isEmpty())
        ) {
            return new Statistic();
        }

        Map<String, DoubleSummaryStatistics> statisticsMap = new HashMap<>();
        List<GoalieSeason> seasons =
                entities
                        .stream()
                        .map(
                                goalie ->
                                        goalie.getSeasonForSeasonString(seasonString))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());

        CoreConstants.GOALIE_SEASON_COLLECTORS.forEach(
                (attribute, collector) -> statisticsMap.put(attribute, new StatisticsUtils<GoalieSeason>().calculateStatistics(attribute, collector, seasons))
        );

        return new Statistic(statisticsMap);
    }
}
