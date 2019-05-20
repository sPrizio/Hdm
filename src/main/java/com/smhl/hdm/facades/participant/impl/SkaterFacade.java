package com.smhl.hdm.facades.participant.impl;

import com.google.common.collect.Sets;
import com.smhl.hdm.converters.participant.impl.SkaterConverter;
import com.smhl.hdm.facades.participant.ParticipantFacade;
import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.resources.participant.impl.SkaterResource;
import com.smhl.hdm.service.participant.impl.SkaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Facade for skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterFacade implements ParticipantFacade<SkaterResource> {

    private SkaterConverter skaterConverter;
    private SkaterService skaterService;

    @Autowired
    public SkaterFacade(SkaterConverter skaterConverter, SkaterService skaterService) {
        this.skaterConverter = skaterConverter;
        this.skaterService = skaterService;
    }

    @Override
    public List<SkaterResource> findAllParticipantsForSeason(String seasonString, String field, String order) {
        return this.skaterConverter.convertAllForSeason(seasonString, Sets.newHashSet(this.skaterService.getAllParticipantsForSeason(seasonString, field, order)));
    }

    @Override
    public SkaterResource find(Long id) {

        Optional<Skater> skater = this.skaterService.find(id);

        if (skater.isPresent()) {
            return this.skaterConverter.convert(skater.get());
        }

        return new SkaterResource();
    }

    @Override
    public List<SkaterResource> findAll() {
        return this.skaterConverter.convertAll(Sets.newHashSet(this.skaterService.findAll()));
    }
}
