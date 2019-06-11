package com.smhl.hdm.facades.nonentities.milestone.impl;

import com.smhl.hdm.facades.nonentities.milestone.MilestoneFacade;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.nonentities.Milestone;
import com.smhl.hdm.service.entities.participant.impl.GoalieService;
import com.smhl.hdm.service.nonentities.milestone.impl.GoalieMilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of the milestone facade for goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GoalieMilestoneFacade implements MilestoneFacade {

    private GoalieService goalieService;
    private GoalieMilestoneService goalieMilestoneService;

    @Autowired
    public GoalieMilestoneFacade(GoalieService goalieService, GoalieMilestoneService goalieMilestoneService) {
        this.goalieService = goalieService;
        this.goalieMilestoneService = goalieMilestoneService;
    }


    @Override
    public Milestone obtainMilestone(String stat, Long id) {
        Optional<Goalie> goalie = this.goalieService.find(id);
        return goalie.isPresent() ? this.goalieMilestoneService.calculateMilestone(stat, goalie.get()) : new Milestone();
    }
}
