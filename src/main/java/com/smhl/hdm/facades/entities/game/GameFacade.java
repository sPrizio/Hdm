package com.smhl.hdm.facades.entities.game;

import com.smhl.hdm.converters.details.participant.GoalieGameDetailsConverter;
import com.smhl.hdm.converters.details.participant.SkaterGameDetailsConverter;
import com.smhl.hdm.converters.game.GameConverter;
import com.smhl.hdm.facades.entities.HdmFacade;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.models.nonentities.star.impl.GameStar;
import com.smhl.hdm.resources.details.participant.GoalieGameDetailsResource;
import com.smhl.hdm.resources.details.participant.SkaterGameDetailsResource;
import com.smhl.hdm.resources.game.GameResource;
import com.smhl.hdm.service.entities.game.GameService;
import com.smhl.hdm.service.nonentities.star.GameStarService;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private GameStarService gameStarService;
    private SkaterGameDetailsConverter skaterGameDetailsConverter;
    private GoalieGameDetailsConverter goalieGameDetailsConverter;

    @Autowired
    public GameFacade(GameService gameService, GameConverter gameConverter, GameStarService gameStarService, SkaterGameDetailsConverter skaterGameDetailsConverter, GoalieGameDetailsConverter goalieGameDetailsConverter) {
        this.gameService = gameService;
        this.gameConverter = gameConverter;
        this.gameStarService = gameStarService;
        this.skaterGameDetailsConverter = skaterGameDetailsConverter;
        this.goalieGameDetailsConverter = goalieGameDetailsConverter;
    }

    public List<GameStar> find3StarsForGame(GameResource gameResource) {

        Optional<Game> game = this.gameService.find(gameResource.getCode());

        if (game.isPresent()) {
            return this.gameStarService.calculateStars(game.get());
        }

        return new ArrayList<>();
    }

    public GameResource findLatestCompletedGame() {
        return this.gameConverter.convert(this.gameService.findLatestCompletedGame());
    }

    public List<GoalieGameDetailsResource> findRecentGameDetailsForGoalie(Long id, Integer limit) {
        return this.goalieGameDetailsConverter.convertAll(this.gameService.findGoalieGameDetails(HdmUtils.getCurrentSeasonString(), id, limit));
    }

    public List<SkaterGameDetailsResource> findRecentGameDetailsForSkater(Long id, Integer limit) {
        return this.skaterGameDetailsConverter.convertAll(this.gameService.findSkaterGameDetails(HdmUtils.getCurrentSeasonString(), id, limit));
    }

    public List<GameResource> findForSeason(String seasonString) {
        return this.gameConverter.convertAll(this.gameService.findBySeasonString(seasonString));
    }

    public GameResource complete(Long id, Map<String, Object> params) {

        Optional<Game> game = this.gameService.find(id);

        if (game.isPresent()) {

            Game completed = this.gameService.complete(game.get(), params);

            if (completed != null) {
                return this.gameConverter.convert(completed);
            }
        }

        return null;
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

    @Override
    public GameResource create(Map<String, Object> params) {

        Game game = this.gameService.create(params);

        if (game != null) {
            return this.gameConverter.convert(game);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        this.gameService.delete(id);
    }
}
