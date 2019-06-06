package com.smhl.hdm.converters.details.participant;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.converters.participant.impl.GoalieConverter;
import com.smhl.hdm.converters.participant.impl.TeamConverter;
import com.smhl.hdm.models.entities.details.participant.GoalieGameDetails;
import com.smhl.hdm.resources.details.participant.GoalieGameDetailsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Convert for goalie game details resources
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GoalieGameDetailsConverter implements HdmConverter<GoalieGameDetails, GoalieGameDetailsResource> {

    private GoalieConverter goalieConverter;
    private TeamConverter teamConverter;

    @Autowired
    public GoalieGameDetailsConverter(GoalieConverter goalieConverter, TeamConverter teamConverter) {
        this.goalieConverter = goalieConverter;
        this.teamConverter = teamConverter;
    }


    @Override
    public GoalieGameDetailsResource convert(GoalieGameDetails entity) {

        GoalieGameDetailsResource resource = new GoalieGameDetailsResource();

        if (Objects.nonNull(entity)) {
            resource.setCode(entity.getId());
            resource.setGoalie(this.goalieConverter.convert(entity.getGoalie()));
            resource.setTeam(this.teamConverter.convert(entity.getTeam()));
            resource.setStarter(entity.isStarter());
            resource.setGameResult(entity.getGameResult());
            resource.setShotsAgainst(entity.getShotsAgainst());
            resource.setSaves(entity.getSaves());
            resource.setGoalsAgainst(entity.getGoalsAgainst());
        }

        return resource;
    }

    @Override
    public List<GoalieGameDetailsResource> convertAll(List<GoalieGameDetails> entity) {

        List<GoalieGameDetailsResource> resources = new ArrayList<>();

        entity.forEach(goalieGameDetails -> resources.add(convert(goalieGameDetails)));

        return resources;
    }
}
