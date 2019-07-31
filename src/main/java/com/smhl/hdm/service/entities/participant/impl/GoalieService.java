package com.smhl.hdm.service.entities.participant.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.enums.GameResult;
import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.entities.season.impl.GoalieSeason;
import com.smhl.hdm.repositories.participant.goalie.GoalieRepository;
import com.smhl.hdm.service.entities.participant.ParticipantService;
import com.smhl.hdm.service.entities.season.impl.GoalieSeasonService;
import com.smhl.hdm.translators.participant.GoalieTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implements the participant service for goalies. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
@SuppressWarnings("Duplicates")
public class GoalieService implements ParticipantService<Goalie, GoalieGameDetails> {

    private GoalieRepository goalieRepository;
    private GoalieSeasonService goalieSeasonService;
    private GoalieTranslator goalieTranslator;

    @Autowired
    public GoalieService(GoalieRepository goalieRepository, GoalieSeasonService goalieSeasonService, GoalieTranslator goalieTranslator) {
        this.goalieRepository = goalieRepository;
        this.goalieSeasonService = goalieSeasonService;
        this.goalieTranslator = goalieTranslator;
    }


    //  METHODS

    @Override
    public void updateStats(GoalieGameDetails details) {

        String seasonString = HdmUtils.getSeasonStringForLocalDateTime(details.getGameTime());
        GoalieSeason season = details.getParticipant().getSeasonForSeasonString(seasonString);

        if (season == null) {
            season = new GoalieSeason();
            season.setSeasonString(seasonString);

            Goalie goalie = details.getParticipant();
            goalie.addSeason(season);

            this.goalieRepository.save(goalie);
        }

        season.incrementGamesPlayed();

        if (details.getIsStarter()) {
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

    @Override
    public List<Goalie> getAllParticipantsForSeason(String seasonString, String field, String order) {
        return this.goalieRepository.findBySeasonStringSorted(seasonString, field, order);
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

    @Override
    public Goalie create(Map<String, Object> params) {

        Goalie goalie = this.goalieTranslator.translate(params);

        if (goalie != null) {
            return this.goalieRepository.save(goalie);
        }

        return null;
    }

    /**
     * Returns the top goalies for a given stat and limited by a number of results
     *
     * @param stat  field to base goalies on
     * @param limit integer limit of results
     * @return limited list
     */
    public List<Goalie> getTopGoaliesForStatAndLimit(String stat, Integer limit) {
        return this.goalieRepository.findTopGoaliesForStatAndLimit(stat, limit);
    }
}
