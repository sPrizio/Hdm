package com.smhl.hdm.facades.entities.game;

import com.smhl.hdm.converters.details.participant.GoalieGameDetailsConverter;
import com.smhl.hdm.converters.details.participant.SkaterGameDetailsConverter;
import com.smhl.hdm.converters.game.GameConverter;
import com.smhl.hdm.facades.entities.HdmFacade;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.resources.details.participant.GoalieGameDetailsResource;
import com.smhl.hdm.resources.details.participant.SkaterGameDetailsResource;
import com.smhl.hdm.resources.game.GameResource;
import com.smhl.hdm.service.entities.game.GameService;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Facade layer for Games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameFacade implements HdmFacade<GameResource> {

    private GameService gameService;
    private GameConverter gameConverter;
    private SkaterGameDetailsConverter skaterGameDetailsConverter;
    private GoalieGameDetailsConverter goalieGameDetailsConverter;

    @Autowired
    public GameFacade(GameService gameService, GameConverter gameConverter, SkaterGameDetailsConverter skaterGameDetailsConverter, GoalieGameDetailsConverter goalieGameDetailsConverter) {
        this.gameService = gameService;
        this.gameConverter = gameConverter;
        this.skaterGameDetailsConverter = skaterGameDetailsConverter;
        this.goalieGameDetailsConverter = goalieGameDetailsConverter;
    }


    public List<GoalieGameDetailsResource> findRecentGameDetailsForGoalie(Long id, int limit) {
        return this.goalieGameDetailsConverter.convertAll(this.gameService.findGoalieGameDetails(HdmUtils.getCurrentSeasonString(), id, limit));
    }

    public List<SkaterGameDetailsResource> findRecentGameDetailsForSkater(Long id, int limit) {
        return this.skaterGameDetailsConverter.convertAll(this.gameService.findSkaterGameDetails(HdmUtils.getCurrentSeasonString(), id, limit));
    }

    public GameResource findForSeason(String seasonString) {
        return this.gameConverter.convert(this.gameService.findBySeasonString(seasonString));
    }

    @Override
    public GameResource find(Long id) {

        Optional<Game> game = this.gameService.find(id);

        if (game.isPresent()) {
            return this.gameConverter.convert(game.get());
        }

        return new GameResource();
    }

    @Override
    public List<GameResource> findAll() {
        return this.gameConverter.convertAll(this.gameService.findAll());
    }
}
