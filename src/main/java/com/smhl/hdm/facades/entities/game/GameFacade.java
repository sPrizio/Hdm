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
 * Facade layer for Games. Documentation for the overridden methods can be located in the interface
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


    //  METHODS

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

    /**
     * Find the 3 stars for a game. We identify a star in a game as a participant who's statistical relevance was above average
     *
     * @param gameResource game that we're looking at specifically
     * @return a list of 3 game stars, the top statistical performers in a game
     */
    public List<GameStar> find3StarsForGame(GameResource gameResource) {

        Optional<Game> game = this.gameService.find(gameResource.getCode());

        if (game.isPresent()) {
            return this.gameStarService.calculateStars(game.get());
        }

        return new ArrayList<>();
    }

    /**
     * Finds the most recently completed game for the current season
     *
     * @return game who's status is completed and is the most recent in the system
     */
    public GameResource findLatestCompletedGame() {
        return this.gameConverter.convert(this.gameService.findLatestCompletedGame());
    }

    /**
     * Finds the n number of games for the given goalie id in descending order of completion by date
     *
     * @param id goalie id
     * @param limit number of games we wish to return, typical number is 5
     * @return list of games that a goalie partook in, order by most recent
     */
    public List<GoalieGameDetailsResource> findRecentGameDetailsForGoalie(Long id, Integer limit) {
        return this.goalieGameDetailsConverter.convertAll(this.gameService.findGoalieGameDetails(HdmUtils.getCurrentSeasonString(), id, limit));
    }

    /**
     * Finds the n number of games for the given skater id in descending order of completion by date
     *
     * @param id skater id
     * @param limit number of games we wish to return, typical number is 5
     * @return list of games that a skater partook in, order by most recent
     */
    public List<SkaterGameDetailsResource> findRecentGameDetailsForSkater(Long id, Integer limit) {
        return this.skaterGameDetailsConverter.convertAll(this.gameService.findSkaterGameDetails(HdmUtils.getCurrentSeasonString(), id, limit));
    }

    /**
     * Finds all games for the given season string
     *
     * @param seasonString season that we're looking at
     * @return list of games who's season string matches that of the given one
     */
    public List<GameResource> findForSeason(String seasonString) {
        return this.gameConverter.convertAll(this.gameService.findBySeasonString(seasonString));
    }

    /**
     * Completes a game, meaning that we wish to submit the statistical values for the game and its participants to the system
     * and capture their values in the database
     *
     * @param id game id
     * @param params statistical information used to complete the game
     * @return game with updated statistical values after completion
     */
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
}
