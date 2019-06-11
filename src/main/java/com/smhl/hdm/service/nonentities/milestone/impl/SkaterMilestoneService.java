package com.smhl.hdm.service.nonentities.milestone.impl;

import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.nonentities.Milestone;
import com.smhl.hdm.service.nonentities.milestone.MilestoneService;
import com.smhl.hdm.utils.MilestoneUtils;
import org.springframework.stereotype.Service;

/**
 * Implementation of the milestone service for skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SkaterMilestoneService implements MilestoneService<Skater> {

    @Override
    public Milestone calculateMilestone(String stat, Skater entity) {

        int value;

        switch (stat) {
            case "gp":
                value = entity.getCurrentSeason().getGamesPlayed();
                break;
            case "goals":
                value = entity.getCurrentSeason().getGoals();
                break;
            case "assists":
                value = entity.getCurrentSeason().getAssists();
                break;
            case "points":
                value = entity.getCurrentSeason().getPoints();
                break;
            default:
                return new Milestone();
        }

        return new Milestone(stat, value, MilestoneUtils.calculatePlateau(value));
    }
}
