package com.smhl.hdm.facades.participant.impl;

import com.smhl.hdm.converters.participant.impl.TeamConverter;
import com.smhl.hdm.facades.participant.ParticipantFacade;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import com.smhl.hdm.service.participant.impl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Facade for teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class TeamFacade implements ParticipantFacade<TeamResource> {

    private TeamConverter teamConverter;
    private TeamService teamService;

    @Autowired
    public TeamFacade(TeamConverter teamConverter, TeamService teamService) {
        this.teamConverter = teamConverter;
        this.teamService = teamService;
    }


    @Override
    public List<TeamResource> findAllParticipantsForSeason(String seasonString, String field, String order) {
        return this.teamConverter.convertAllForSeason(seasonString, this.teamService.getAllParticipantsForSeason(seasonString, field, order));
    }

    @Override
    public TeamResource find(Long id) {

        Optional<Team> team = this.teamService.find(id);

        if (team.isPresent()) {
            return this.teamConverter.convert(team.get());
        }

        return new TeamResource();
    }

    @Override
    public List<TeamResource> findAll() {
        return this.teamConverter.convertAll(this.teamService.findAll());
    }
}
