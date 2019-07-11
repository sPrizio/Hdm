package com.smhl.hdm.service.entities.game;

import com.google.common.collect.Lists;
import com.smhl.hdm.enums.GameStatus;
import com.smhl.hdm.models.entities.details.game.GameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.repositories.game.GameRepository;
import com.smhl.hdm.service.entities.HdmService;
import com.smhl.hdm.service.entities.participant.impl.GoalieService;
import com.smhl.hdm.service.entities.participant.impl.SkaterService;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.translators.details.game.GameDetailsTranslator;
import com.smhl.hdm.translators.game.GameTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service-layer for games. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GameService implements HdmService<Game> {

    private SkaterService skaterService;
    private GoalieService goalieService;
    private TeamService teamService;
    private GameRepository gameRepository;
    private GameTranslator gameTranslator;
    private GameDetailsTranslator gameDetailsTranslator;

    @Autowired
    public GameService(SkaterService skaterService, GoalieService goalieService, TeamService teamService, GameRepository gameRepository, GameTranslator gameTranslator, GameDetailsTranslator gameDetailsTranslator) {
        this.skaterService = skaterService;
        this.goalieService = goalieService;
        this.teamService = teamService;
        this.gameRepository = gameRepository;
        this.gameTranslator = gameTranslator;
        this.gameDetailsTranslator = gameDetailsTranslator;
    }


    //  METHODS

    @Override
    public void refresh(Game entity) {
        this.gameRepository.refresh(entity);
    }

    @Override
    public Optional<Game> find(Long id) {
        return this.gameRepository.findById(id);
    }

    @Override
    public List<Game> findAll() {
        return Lists.newArrayList(this.gameRepository.findAll());
    }

    @Override
    public Game save(Game entity) {
        return this.gameRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

        if (find(id).isPresent()) {
            this.gameRepository.deleteById(id);
        }
    }

    @Override
    public Game create(Map<String, Object> params) {

        Game game = this.gameTranslator.translate(params);

        if (game != null) {
            return this.gameRepository.save(game);
        }

        return null;
    }

    /**
     * Finds a game by it's date & time
     *
     * @param gameTime date & time we wish to identify the game by
     * @return game if found, null otherwise
     */
    public Game findByGameTime(LocalDateTime gameTime) {
        return this.gameRepository.findByGameTime(gameTime);
    }

    /**
     * Finds the most recent game who's status is 'COMPLETE'
     *
     * @return game with the status of complete and that is the most recent
     */
    public Game findLatestCompletedGame() {
        return this.gameRepository.findFirstBySeasonStringAndGameStatusOrderByGameTimeDesc(HdmUtils.getCurrentSeasonString(), GameStatus.COMPLETE.toString());
    }

    /**
     * Finds all skater game details for the given game
     *
     * @param seasonString season that we're looking at
     * @param id game id
     * @param limit number of results that we want
     * @return list of skater game details for the given game
     */
    public List<SkaterGameDetails> findSkaterGameDetails(String seasonString, Long id, Integer limit) {
        return this.gameRepository.findSkaterGameDetails(seasonString, id, limit);
    }

    /**
     * Finds all goalie game details for the given game
     *
     * @param seasonString season that we're looking at
     * @param id game id
     * @param limit number of results that we want
     * @return list of goalie game details for the given game
     */
    public List<GoalieGameDetails> findGoalieGameDetails(String seasonString, Long id, Integer limit) {
        return this.gameRepository.findGoalieGameDetails(seasonString, id, limit);
    }

    /**
     * Finds a list of games by their season string
     *
     * @param seasonString season that we're looking at
     * @return a list of games with a season string matching that of the given season string
     */
    public List<Game> findBySeasonString(String seasonString) {
        return this.gameRepository.findBySeasonStringOrderByGameTimeDesc(seasonString);
    }

    /**
     * Completes a game, i.e. associates the given information with the various participants' and game's stats
     *
     * @param game game that we're completing
     * @param values statistical information being given to the game
     * @return newly updated game
     */
    public Game complete(Game game, Map<String, Object> values) {

        GameDetails gameDetails = this.gameDetailsTranslator.translate(values);

        if (gameDetails != null) {

            //  update the stats for each participant
            gameDetails.getSkaterGameDetails()
                    .forEach(details -> this.skaterService.updateStats(details));

            gameDetails.getGoalieGameDetails()
                    .forEach(details -> this.goalieService.updateStats(details));

            gameDetails.getTeamGameDetails()
                    .forEach(details -> this.teamService.updateStats(details));

            game.setGameDetails(gameDetails);
            game.setGameStatus(GameStatus.COMPLETE.toString());

            return save(game);
        }

        return game;
    }
}
