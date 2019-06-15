package com.smhl.hdm.converters.details.participant;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.converters.participant.impl.TeamConverter;
import com.smhl.hdm.models.entities.details.participant.impl.TeamGameDetails;
import com.smhl.hdm.resources.details.participant.TeamGameDetailsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Converter for team game details resources
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class TeamGameDetailsConverter implements HdmConverter<TeamGameDetails, TeamGameDetailsResource> {

    private TeamConverter teamConverter;

    @Autowired
    public TeamGameDetailsConverter(TeamConverter teamConverter) {
        this.teamConverter = teamConverter;
    }

    @Override
    public TeamGameDetailsResource convert(TeamGameDetails entity) {

        TeamGameDetailsResource resource = new TeamGameDetailsResource();

        if (Objects.nonNull(entity)) {
            resource.setCode(entity.getId());
            resource.setGameTime(entity.getGameTime());
            resource.setTeam(this.teamConverter.convert(entity.getParticipant()));
            resource.setGameResult(entity.getGameResult());
            resource.setGoalsFor(entity.getGoalsFor());
            resource.setGoalsAgainst(entity.getGoalsAgainst());
        }

        return resource;
    }

    @Override
    public List<TeamGameDetailsResource> convertAll(List<TeamGameDetails> entity) {

        List<TeamGameDetailsResource> resources = new ArrayList<>();

        entity.forEach(teamGameDetails -> resources.add(convert(teamGameDetails)));

        return resources;
    }
}
