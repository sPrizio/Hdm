package com.smhl.hdm.converters.game;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.converters.details.game.GameDetailsConverter;
import com.smhl.hdm.converters.participant.impl.TeamConverter;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.resources.game.GameResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of a converter for Games. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameConverter implements HdmConverter<Game, GameResource> {

    private TeamConverter teamConverter;
    private GameDetailsConverter gameDetailsConverter;

    @Autowired
    public GameConverter(TeamConverter teamConverter, GameDetailsConverter gameDetailsConverter) {
        this.teamConverter = teamConverter;
        this.gameDetailsConverter = gameDetailsConverter;
    }


    //  METHODS

    @Override
    public GameResource convert(Game entity) {

        GameResource resource = new GameResource();

        if (Objects.nonNull(entity)) {
            resource.setCode(entity.getId());
            resource.setGameTime(entity.getGameTime());
            resource.setSeasonString(entity.getSeasonString());
            resource.setGameStatus(entity.getGameStatus());
            resource.setHomeTeam(this.teamConverter.convert(entity.getHomeTeam()));
            resource.setAwayTeam(this.teamConverter.convert(entity.getAwayTeam()));
            resource.setGameDetails(this.gameDetailsConverter.convert(entity.getGameDetails()));
        }

        return resource;
    }

    @Override
    public List<GameResource> convertAll(List<Game> entity) {

        List<GameResource> resources = new ArrayList<>();

        entity.forEach(game -> resources.add(convert(game)));

        return resources;
    }
}
