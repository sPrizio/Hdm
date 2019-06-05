package com.smhl.hdm.facades.nonentities.statistics.impl;

import com.smhl.hdm.facades.nonentities.statistics.StatisticsFacade;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.nonentities.Statistic;
import com.smhl.hdm.service.entities.participant.impl.GoalieService;
import com.smhl.hdm.service.nonentities.statistic.impl.GoalieStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Obtains statistics for goalies for use in controllers
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GoalieStatisticsFacade implements StatisticsFacade {

    private GoalieService goalieService;
    private GoalieStatisticsService goalieStatisticsService;

    @Autowired
    public GoalieStatisticsFacade(GoalieService goalieService, GoalieStatisticsService goalieStatisticsService) {
        this.goalieService = goalieService;
        this.goalieStatisticsService = goalieStatisticsService;
    }


    @Override
    public Statistic obtainStatistics(Long id) {
        Optional<Goalie> goalie = this.goalieService.find(id);
        return goalie.isPresent() ? this.goalieStatisticsService.calculate(goalie.get()) : new Statistic();
    }

    @Override
    public Statistic obtainActiveStatistics(String seasonString) {
        return this.goalieStatisticsService.calculate(this.goalieService.getAllParticipantsForSeason(seasonString, "wins", "desc"), seasonString);
    }
}
