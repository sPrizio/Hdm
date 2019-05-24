package com.smhl.hdm.converters.season;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.models.season.impl.GoalieSeason;
import com.smhl.hdm.resources.season.GoalieSeasonResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Converter for goalie season resources
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GoalieSeasonConverter implements HdmConverter<GoalieSeason, GoalieSeasonResource> {

    @Override
    public GoalieSeasonResource convert(GoalieSeason entity) {

        GoalieSeasonResource resource = new GoalieSeasonResource();

        if (Objects.nonNull(entity)) {
            resource.setSeasonString(entity.getSeasonString());
            resource.setGamesPlayed(entity.getGamesPlayed());
            resource.setGamesStarted(entity.getGamesStarted());
            resource.setWins(entity.getWins());
            resource.setLosses(entity.getLosses());
            resource.setTies(entity.getTies());
            resource.setSaves(entity.getSaves());
            resource.setShotsAgainst(entity.getShotsAgainst());
            resource.setGoalsAgainst(entity.getGoalsAgainst());
            resource.setShutouts(entity.getShotsAgainst());
            resource.setSavePercentage(entity.getSavePercentage());
            resource.setGoalsAgainstAverage(entity.getGoalsAgainstAverage());
        }

        return resource;
    }

    @Override
    public List<GoalieSeasonResource> convertAll(List<GoalieSeason> entity) {

        List<GoalieSeasonResource> resources = new ArrayList<>();

        entity.forEach(goalieSeason -> resources.add(convert(goalieSeason)));

        return resources;
    }
}
