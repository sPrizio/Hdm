package com.smhl.hdm.service.participant.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.enums.GameResult;
import com.smhl.hdm.models.details.participant.TeamGameDetails;
import com.smhl.hdm.models.participant.impl.Team;
import com.smhl.hdm.models.season.impl.TeamSeason;
import com.smhl.hdm.repositories.participant.TeamRepository;
import com.smhl.hdm.service.participant.ParticipantService;
import com.smhl.hdm.service.season.impl.TeamSeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation for the team service class
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class TeamService implements ParticipantService<Team, TeamGameDetails> {

    private TeamRepository teamRepository;
    private TeamSeasonService teamSeasonService;

    @Autowired
    public TeamService(TeamRepository teamRepository, TeamSeasonService teamSeasonService) {
        this.teamRepository = teamRepository;
        this.teamSeasonService = teamSeasonService;
    }

    @Override
    public void updateStats(TeamGameDetails details) {

        TeamSeason season = details.getTeam().getCurrentSeason();

        if (season != null) {

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
    }

    @Override
    public List<Team> getAllActiveParticipants(String field, String order) {
        return this.teamRepository.getAllByActive(true);
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
}
