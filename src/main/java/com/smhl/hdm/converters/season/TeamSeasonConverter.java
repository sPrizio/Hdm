package com.smhl.hdm.converters.season;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.models.entities.season.impl.TeamSeason;
import com.smhl.hdm.resources.season.TeamSeasonResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Converter for team season resources
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class TeamSeasonConverter implements HdmConverter<TeamSeason, TeamSeasonResource> {

    @Override
    public TeamSeasonResource convert(TeamSeason entity) {

        TeamSeasonResource resource = new TeamSeasonResource();

        if (Objects.nonNull(entity)) {
            resource.setSeasonString(entity.getSeasonString());
            resource.setGamesPlayed(entity.getGamesPlayed());
            resource.setWins(entity.getWins());
            resource.setLosses(entity.getLosses());
            resource.setTies(entity.getTies());
            resource.setPoints(calculatePoints(entity));
            resource.setGoalsFor(entity.getGoalsFor());
            resource.setGoalsAgainst(entity.getGoalsAgainst());
            resource.setDifferential((entity.getGoalsFor() - entity.getGoalsAgainst()));
        }

        return resource;
    }

    @Override
    public List<TeamSeasonResource> convertAll(List<TeamSeason> entity) {

        List<TeamSeasonResource> resources = new ArrayList<>();

        entity.forEach(teamSeason -> resources.add(convert(teamSeason)));

        return resources;
    }


    //  HELPERS

    private Integer calculatePoints(TeamSeason entity) {
        return (entity.getWins() * 2) + entity.getTies();
    }
}
