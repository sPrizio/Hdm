package com.smhl.hdm.service.entities.participant.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.enums.GameResult;
import com.smhl.hdm.models.entities.details.participant.impl.TeamGameDetails;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.models.entities.season.impl.TeamSeason;
import com.smhl.hdm.repositories.participant.team.TeamRepository;
import com.smhl.hdm.service.entities.participant.ParticipantService;
import com.smhl.hdm.service.entities.season.impl.TeamSeasonService;
import com.smhl.hdm.translators.participant.TeamTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation for the team service class. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
@SuppressWarnings("Duplicates")
public class TeamService implements ParticipantService<Team, TeamGameDetails> {

    private TeamRepository teamRepository;
    private TeamSeasonService teamSeasonService;
    private TeamTranslator teamTranslator;

    @Autowired
    public TeamService(TeamRepository teamRepository, TeamSeasonService teamSeasonService, TeamTranslator teamTranslator) {
        this.teamRepository = teamRepository;
        this.teamSeasonService = teamSeasonService;
        this.teamTranslator = teamTranslator;
    }


    //  METHODS

    @Override
    public void updateStats(TeamGameDetails details) {

        String seasonString = HdmUtils.getSeasonStringForLocalDateTime(details.getGameTime());
        TeamSeason season = details.getParticipant().getSeasonForSeasonString(seasonString);

        if (season == null) {
            season = new TeamSeason();
            season.setSeasonString(seasonString);

            Team team = details.getParticipant();
            team.addSeason(season);

            this.teamRepository.save(team);
        }

        season.incrementGamesPlayed();

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

        season.incrementGoalsFor(details.getGoalsFor());
        season.incrementGoalsAgainst(details.getGoalsAgainst());

        this.teamSeasonService.save(season);
    }

    @Override
    public List<Team> getAllParticipantsForSeason(String seasonString, String field, String order) {
        return this.teamRepository.findBySeasonStringSorted(seasonString, field, order);
    }

    @Override
    public void refresh(Team entity) {
        this.teamRepository.refresh(entity);
    }

    @Override
    public Optional<Team> find(Long id) {
        return this.teamRepository.findById(id);
    }

    @Override
    public List<Team> findAll() {
        return Lists.newArrayList(this.teamRepository.findAll());
    }

    @Override
    public Team save(Team entity) {
        return this.teamRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

        if (find(id).isPresent()) {
            this.teamRepository.deleteById(id);
        }
    }

    @Override
    public Team create(Map<String, Object> params) {

        Team team = this.teamTranslator.translate(params);

        if (team != null) {
            return this.teamRepository.save(team);
        }

        return null;
    }
}
