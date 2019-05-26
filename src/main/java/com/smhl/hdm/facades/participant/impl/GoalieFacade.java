package com.smhl.hdm.facades.participant.impl;

import com.smhl.hdm.converters.participant.impl.GoalieConverter;
import com.smhl.hdm.facades.participant.ParticipantFacade;
import com.smhl.hdm.models.participant.impl.Goalie;
import com.smhl.hdm.resources.participant.impl.GoalieResource;
import com.smhl.hdm.service.participant.impl.GoalieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Facade for goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GoalieFacade implements ParticipantFacade<GoalieResource> {

    private GoalieConverter goalieConverter;
    private GoalieService goalieService;

    @Autowired
    public GoalieFacade(GoalieConverter goalieConverter, GoalieService goalieService) {
        this.goalieConverter = goalieConverter;
        this.goalieService = goalieService;
    }

    /**
     * Returns the top goalies for a given stat and limited by a number of results
     *
     * @param stat field to base goalies on
     * @param limit integer limit of results
     * @return limited list
     */
    public List<GoalieResource> findTopGoaliesForStatAndLimit(String stat, int limit) {
       return this.goalieConverter.convertAll(this.goalieService.getTopGoaliesForStatAndLimit(stat, limit));
    }

    @Override
    public List<GoalieResource> findAllParticipantsForSeason(String seasonString, String field, String order) {
        return this.goalieConverter.convertAllForSeason(seasonString, this.goalieService.getAllParticipantsForSeason(seasonString, field, order));
    }

    @Override
    public GoalieResource find(Long id) {

        Optional<Goalie> goalie = this.goalieService.find(id);

        if (goalie.isPresent()) {
            return this.goalieConverter.convert(goalie.get());
        }

        return new GoalieResource();
    }

    @Override
    public List<GoalieResource> findAll() {
        return this.goalieConverter.convertAll(this.goalieService.findAll());
    }
}