package com.smhl.hdm.translators.details.participant;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.details.participant.impl.TeamGameDetails;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.translators.HdmTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Implementation of a translator for TeamGameDetails. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class TeamGameDetailsTranslator implements HdmTranslator<TeamGameDetails> {

    private TeamService teamService;

    @Autowired
    public TeamGameDetailsTranslator(TeamService teamService) {
        this.teamService = teamService;
    }


    //  METHODS

    @Override
    public TeamGameDetails translate(Map<String, Object> values) {

        Optional<Team> team = this.teamService.find(Long.parseLong(values.get("participant").toString()));

        if (team.isEmpty()) {
            return null;
        }

        return new TeamGameDetails(
                HdmUtils.parse(values.get("gameTime").toString(), CoreConstants.DATE_FORMAT_LONG),
                team.get(),
                values.get("gameResult").toString(),
                Integer.parseInt(values.get("goalsFor").toString()),
                Integer.parseInt(values.get("goalsAgainst").toString())
        );
    }
}
