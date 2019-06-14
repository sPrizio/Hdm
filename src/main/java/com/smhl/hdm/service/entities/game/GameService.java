package com.smhl.hdm.service.entities.game;

import com.google.common.collect.Lists;
import com.smhl.hdm.enums.GameStatus;
import com.smhl.hdm.models.entities.details.participant.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.SkaterGameDetails;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.repositories.game.GameRepository;
import com.smhl.hdm.service.entities.HdmService;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service-layer for games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GameService implements HdmService<Game> {

    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game findLatestCompletedGame() {
        return this.gameRepository.findFirstBySeasonStringAndGameStatusOrderByGameTimeDesc(HdmUtils.getCurrentSeasonString(), GameStatus.COMPLETE.toString());
    }

    public List<SkaterGameDetails> findSkaterGameDetails(String seasonString, Long id, int limit) {
        return this.gameRepository.findSkaterGameDetails(seasonString, id, limit);
    }

    public List<GoalieGameDetails> findGoalieGameDetails(String seasonString, Long id, int limit) {
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
}
