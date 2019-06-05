package com.smhl.hdm.converters.participant.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.smhl.hdm.converters.participant.ParticipantConverter;
import com.smhl.hdm.converters.season.GoalieSeasonConverter;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.resources.participant.impl.GoalieResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Converter for goalie entities
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GoalieConverter implements ParticipantConverter<Goalie, GoalieResource> {

    private GoalieSeasonConverter goalieSeasonConverter;

    @Autowired
    public GoalieConverter(GoalieSeasonConverter goalieSeasonConverter) {
        this.goalieSeasonConverter = goalieSeasonConverter;
    }


    @Override
    public GoalieResource convert(Goalie entity) {

        GoalieResource resource = convertCore(entity);
        resource.setSeason(this.goalieSeasonConverter.convert(entity.getCurrentSeason()));

        return resource;
    }

    @Override
    public List<GoalieResource> convertAll(List<Goalie> entity) {

        List<GoalieResource> resources = new ArrayList<>();

        entity.forEach(goalie -> resources.add(convert(goalie)));

        return resources;
    }

    @Override
    public GoalieResource convertForSeason(String season, Goalie entity) {

        GoalieResource resource = convertCore(entity);
        resource.setSeason(this.goalieSeasonConverter.convert(entity.getSeasonForSeasonString(season)));

        return resource;
    }

    @Override
    public List<GoalieResource> convertAllForSeason(String season, List<Goalie> entity) {
        List<GoalieResource> resources = new ArrayList<>();

        entity.forEach(goalie -> resources.add(convertForSeason(season, goalie)));

        return resources;
    }


    //  HELPERS

    /**
     * Reduces duplicate code
     *
     * @param entity entity that we're converting
     * @return resource
     */
    private GoalieResource convertCore(Goalie entity) {

        GoalieResource resource = new GoalieResource();

        resource.setCode(entity.getId());
        resource.setName(entity.getName());
        resource.setPosition(entity.getPosition());
        resource.setSeasons(Sets.newHashSet(this.goalieSeasonConverter.convertAll(Lists.newArrayList(entity.getSeasons()))));

        return resource;
    }
}
