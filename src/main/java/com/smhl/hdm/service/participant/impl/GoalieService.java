package com.smhl.hdm.service.participant.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.enums.GameResult;
import com.smhl.hdm.models.details.participant.GoalieGameDetails;
import com.smhl.hdm.models.participant.impl.Goalie;
import com.smhl.hdm.models.season.impl.GoalieSeason;
import com.smhl.hdm.repositories.participant.GoalieRepository;
import com.smhl.hdm.service.participant.ParticipantService;
import com.smhl.hdm.service.season.impl.GoalieSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implements the participant service for goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GoalieService implements ParticipantService<Goalie, GoalieGameDetails> {

    private GoalieRepository goalieRepository;
    private GoalieSeasonService goalieSeasonService;

    @Autowired
    public GoalieService(GoalieRepository goalieRepository, GoalieSeasonService goalieSeasonService) {
        this.goalieRepository = goalieRepository;
        this.goalieSeasonService = goalieSeasonService;
    }

    @Override
    public void updateStats(GoalieGameDetails details) {

        GoalieSeason season = details.getGoalie().getCurrentSeason();

        if (season != null) {

            season.incrementGamesPlayed();

            if (details.isStarter()) {
                season.incrementGamesStarted();
            }

            switch (GameResult.valueOf(details.getGameResult())) {
                case WIN:
                    season.incrementWins();
                    break;
                case LOSS:
                    season.incrementLosses();
                    break;
                case TIE:
                    season.incrementTies();
                    break;
            }

            season.incrementShotsAgainst(details.getShotsAgainst());
            season.incrementSaves(details.getSaves());
            season.incrementGoalsAgainst(details.getGoalsAgainst());

            if (details.getGoalsAgainst() == 0) {
                season.incrementShutouts();
            }

            this.goalieSeasonService.save(season);
        }
    }

    @Override
    public void refresh(Goalie entity) {
        this.goalieRepository.refresh(entity);
    }

    @Override
    public Optional<Goalie> find(Long id) {
        return this.goalieRepository.findById(id);
    }

    @Override
    public List<Goalie> findAll() {
        return Lists.newArrayList(this.goalieRepository.findAll());
    }

    @Override
    public Goalie save(Goalie entity) {
        return this.goalieRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

        if (find(id).isPresent()) {
            this.goalieRepository.deleteById(id);
        }
    }
}
