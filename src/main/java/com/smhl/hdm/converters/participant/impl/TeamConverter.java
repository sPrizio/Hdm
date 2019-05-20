package com.smhl.hdm.converters.participant.impl;

import com.google.common.collect.Sets;
import com.smhl.hdm.converters.participant.ParticipantConverter;
import com.smhl.hdm.converters.season.TeamSeasonConverter;
import com.smhl.hdm.models.participant.impl.Team;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Converter for team entities
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class TeamConverter implements ParticipantConverter<Team, TeamResource> {

    private TeamSeasonConverter teamSeasonConverter;

    @Autowired
    public TeamConverter(TeamSeasonConverter teamSeasonConverter) {
        this.teamSeasonConverter = teamSeasonConverter;
    }

    @Override
    public TeamResource convert(Team entity) {

        TeamResource resource = convertCore(entity);

        resource.setSeason(this.teamSeasonConverter.convert(entity.getCurrentSeason()));

        return resource;
    }

    @Override
    public List<TeamResource> convertAll(Set<Team> entity) {

        List<TeamResource> resources = new ArrayList<>();

        entity.forEach(team -> resources.add(convert(team)));

        return resources;
    }

    @Override
    public TeamResource convertForSeason(String season, Team entity) {

        TeamResource resource = convertCore(entity);

        resource.setSeason(this.teamSeasonConverter.convert(entity.getSeasonForSeasonString(season)));

        return resource;
    }

    @Override
    public List<TeamResource> convertAllForSeason(String season, Set<Team> entity) {
        List<TeamResource> resources = new ArrayList<>();

        entity.forEach(team -> resources.add(convertForSeason(season, team)));

        return resources;
    }


    //  HELPERS

    private TeamResource convertCore(Team entity) {

        TeamResource resource = new TeamResource();

        resource.setName(entity.getName());
        resource.setSeasons(Sets.newHashSet(this.teamSeasonConverter.convertAll(entity.getSeasons())));

        return resource;
    }
}
