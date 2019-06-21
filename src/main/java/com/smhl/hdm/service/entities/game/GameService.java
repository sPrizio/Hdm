package com.smhl.hdm.service.entities.game;

import com.google.common.collect.Lists;
import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.enums.GameStatus;
import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.repositories.game.GameRepository;
import com.smhl.hdm.service.entities.HdmService;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service-layer for games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GameService implements HdmService<Game> {

    private TeamService teamService;
    private GameRepository gameRepository;

    @Autowired
    public GameService(TeamService teamService, GameRepository gameRepository) {
        this.teamService = teamService;
        this.gameRepository = gameRepository;
    }

    public Game findByGameTime(LocalDateTime gameTime) {
        return this.gameRepository.findByGameTime(gameTime);
    }

    public Game findLatestCompletedGame() {
        return this.gameRepository.findFirstBySeasonStringAndGameStatusOrderByGameTimeDesc(HdmUtils.getCurrentSeasonString(), GameStatus.COMPLETE.toString());
    }

    public List<SkaterGameDetails> findSkaterGameDetails(String seasonString, Long id, Integer limit) {
        return this.gameRepository.findSkaterGameDetails(seasonString, id, limit);
    }

    public List<GoalieGameDetails> findGoalieGameDetails(String seasonString, Long id, Integer limit) {
        return this.gameRepository.findGoalieGameDetails(seasonString, id, limit);
    }

    public List<Game> findBySeasonString(String seasonString) {
        return this.gameRepository.findBySeasonStringOrderByGameTimeDesc(seasonString);
    }

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

        String date = params.get("gameTime").toString();
        String season = params.get("seasonString").toString();
        String status = params.get("gameStatus").toString();
        String home = params.get("homeTeam").toString();
        String away = params.get("awayTeam").toString();

        LocalDateTime dateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(CoreConstants.DATE_FORMAT_LONG, CoreConstants.HDM_LOCALE));

        Optional<Team> homeTeam = this.teamService.find(Long.parseLong(home));
        Optional<Team> awayTeam = this.teamService.find(Long.parseLong(away));

        if (homeTeam.isPresent() && awayTeam.isPresent()) {
            Game game = new Game(dateTime, season, status, homeTeam.get(), awayTeam.get());
            return this.gameRepository.save(game);
        }

        return null;
    }
}
