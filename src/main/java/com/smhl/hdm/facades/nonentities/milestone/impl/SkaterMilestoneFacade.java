package com.smhl.hdm.facades.nonentities.milestone.impl;

import com.smhl.hdm.facades.nonentities.milestone.MilestoneFacade;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.nonentities.Milestone;
import com.smhl.hdm.service.entities.participant.impl.SkaterService;
import com.smhl.hdm.service.nonentities.milestone.impl.SkaterMilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of the milestone facade for skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterMilestoneFacade implements MilestoneFacade {

    private SkaterService skaterService;
    private SkaterMilestoneService skaterMilestoneService;

    @Autowired
    public SkaterMilestoneFacade(SkaterService skaterService, SkaterMilestoneService skaterMilestoneService) {
        this.skaterService = skaterService;
        this.skaterMilestoneService = skaterMilestoneService;
    }


    @Override
    public Milestone obtainMilestone(String stat, Long id) {
        Optional<Skater> skater = this.skaterService.find(id);
        return skater.isPresent() ? this.skaterMilestoneService.calculateMilestone(stat, skater.get()) : new Milestone();
    }
}
