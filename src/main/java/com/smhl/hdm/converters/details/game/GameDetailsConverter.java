package com.smhl.hdm.converters.details.game;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.converters.details.participant.GoalieGameDetailsConverter;
import com.smhl.hdm.converters.details.participant.SkaterGameDetailsConverter;
import com.smhl.hdm.converters.details.participant.TeamGameDetailsConverter;
import com.smhl.hdm.converters.game.ScoringPlayConverter;
import com.smhl.hdm.models.entities.details.game.GameDetails;
import com.smhl.hdm.resources.details.game.GameDetailsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Converter for game details resources
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameDetailsConverter implements HdmConverter<GameDetails, GameDetailsResource> {

    private SkaterGameDetailsConverter skaterGameDetailsConverter;
    private GoalieGameDetailsConverter goalieGameDetailsConverter;
    private TeamGameDetailsConverter teamGameDetailsConverter;
    private ScoringPlayConverter scoringPlayConverter;

    @Autowired
    public GameDetailsConverter(SkaterGameDetailsConverter skaterGameDetailsConverter, GoalieGameDetailsConverter goalieGameDetailsConverter, TeamGameDetailsConverter teamGameDetailsConverter, ScoringPlayConverter scoringPlayConverter) {
        this.skaterGameDetailsConverter = skaterGameDetailsConverter;
        this.goalieGameDetailsConverter = goalieGameDetailsConverter;
        this.teamGameDetailsConverter = teamGameDetailsConverter;
        this.scoringPlayConverter = scoringPlayConverter;
    }


    @Override
    public GameDetailsResource convert(GameDetails entity) {

        GameDetailsResource resource = new GameDetailsResource();

        if (Objects.nonNull(entity)) {
            resource.setCode(entity.getId());
            resource.setHomeTeamScore(entity.getHomeTeamScore());
            resource.setAwayTeamScore(entity.getAwayTeamScore());
            resource.setSkaterGameDetails(Sets.newHashSet(this.skaterGameDetailsConverter.convertAll(Lists.newArrayList(entity.getSkaterGameDetails()))));
            resource.setGoalieGameDetails(Sets.newHashSet(this.goalieGameDetailsConverter.convertAll(Lists.newArrayList(entity.getGoalieGameDetails()))));
            resource.setTeamGameDetails(Sets.newHashSet(this.teamGameDetailsConverter.convertAll(Lists.newArrayList(entity.getTeamGameDetails()))));
            resource.setScoringPlays(Sets.newHashSet(this.scoringPlayConverter.convertAll(Lists.newArrayList(entity.getScoringPlays()))));
        }

        return resource;
    }

    @Override
    public List<GameDetailsResource> convertAll(List<GameDetails> entity) {

        List<GameDetailsResource> resources = new ArrayList<>();

        entity.forEach(gameDetails -> resources.add(convert(gameDetails)));

        return resources;
    }
}
