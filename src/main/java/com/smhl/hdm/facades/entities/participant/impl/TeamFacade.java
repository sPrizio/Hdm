package com.smhl.hdm.facades.entities.participant.impl;

import com.smhl.hdm.converters.participant.impl.TeamConverter;
import com.smhl.hdm.facades.entities.participant.ParticipantFacade;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Facade for teams. Documentation for the overridden methods can be located in the interface
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


    //  METHODS

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

    @Override
    public TeamResource create(Map<String, Object> params) {

        Team team = this.teamService.create(params);

        if (team != null) {
            return this.teamConverter.convert(team);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        this.teamService.delete(id);
    }
}
