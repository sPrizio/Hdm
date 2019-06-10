package com.smhl.hdm.converters.participant.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.smhl.hdm.converters.participant.ParticipantConverter;
import com.smhl.hdm.converters.season.SkaterSeasonConverter;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.resources.participant.impl.SkaterResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter for skater entities
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterConverter implements ParticipantConverter<Skater, SkaterResource> {

    private SkaterSeasonConverter skaterSeasonConverter;

    @Autowired
    public SkaterConverter(SkaterSeasonConverter skaterSeasonConverter) {
        this.skaterSeasonConverter = skaterSeasonConverter;
    }

    @Override
    public SkaterResource convert(Skater entity) {

        SkaterResource resource = convertCore(entity);
        resource.setSeason(this.skaterSeasonConverter.convert(entity.getCurrentSeason()));

        return resource;
    }

    @Override
    public List<SkaterResource> convertAll(List<Skater> entity) {

        List<SkaterResource> resources = new ArrayList<>();

        entity.forEach(skater -> resources.add(convert(skater)));

        return resources;
    }

    @Override
    public SkaterResource convertForSeason(String season, Skater entity) {

        SkaterResource resource = convertCore(entity);
        resource.setSeason(this.skaterSeasonConverter.convert(entity.getSeasonForSeasonString(season)));

        return resource;
    }

    @Override
    public List<SkaterResource> convertAllForSeason(String season, List<Skater> entity) {
        List<SkaterResource> resources = new ArrayList<>();

        entity.forEach(skater -> resources.add(convertForSeason(season, skater)));

        return resources;
    }


    //  HELPERS

    /**
     * Reduces duplicate code
     *
     * @param entity entity that we're converting
     * @return resource
     */
    private SkaterResource convertCore(Skater entity) {

        SkaterResource resource = new SkaterResource();

        resource.setCode(entity.getId());
        resource.setName(entity.getName());
        resource.setPosition(entity.getPosition());
        resource.setSeasons(Sets.newTreeSet(this.skaterSeasonConverter.convertAll(Lists.newArrayList(entity.getSeasons()))));

        return resource;
    }
}
