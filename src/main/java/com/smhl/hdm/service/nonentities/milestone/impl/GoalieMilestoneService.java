package com.smhl.hdm.service.nonentities.milestone.impl;

import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.nonentities.Milestone;
import com.smhl.hdm.service.nonentities.milestone.MilestoneService;
import com.smhl.hdm.utils.MilestoneUtils;
import org.springframework.stereotype.Service;

/**
 * Implementation of the milestone service for goalies. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GoalieMilestoneService implements MilestoneService<Goalie> {

    @Override
    public Milestone calculateMilestone(String stat, Goalie entity) {

        int value;

        switch (stat) {
            case "gp":
                value = entity.getCurrentSeason().getGamesPlayed();
                break;
            case "wins":
                value = entity.getCurrentSeason().getWins();
                break;
            case "saves":
                value = entity.getCurrentSeason().getSaves();
                break;
            case "shutouts":
                value = entity.getCurrentSeason().getShutouts();
                break;
            default:
                return new Milestone();
        }

        return new Milestone(stat, value, MilestoneUtils.calculatePlateau(value));
    }
}
