package com.smhl.hdm.translators.details.participant;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.service.entities.participant.impl.GoalieService;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.translators.HdmTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Implementation of a translator for GoalieGameDetails. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GoalieGameDetailsTranslator implements HdmTranslator<GoalieGameDetails> {

    private GoalieService goalieService;
    private TeamService teamService;

    @Autowired
    public GoalieGameDetailsTranslator(GoalieService goalieService, TeamService teamService) {
        this.goalieService = goalieService;
        this.teamService = teamService;
    }


    //  METHODS

    @Override
    public GoalieGameDetails translate(Map<String, Object> values) {

        Optional<Goalie> goalie = this.goalieService.find(Long.parseLong(values.get("participant").toString()));
        Optional<Team> team = this.teamService.find(Long.parseLong(values.get("team").toString()));

        if (goalie.isPresent() && team.isPresent()) {
            return new GoalieGameDetails(
                    HdmUtils.parse(values.get("gameTime").toString(), CoreConstants.DATE_FORMAT_LONG),
                    goalie.get(),
                    team.get(),
                    Boolean.parseBoolean(values.get("isStarter").toString()),
                    values.get("gameResult").toString(),
                    Integer.parseInt(values.get("shotsAgainst").toString()),
                    Integer.parseInt(values.get("saves").toString()),
                    Integer.parseInt(values.get("goalsAgainst").toString())
            );
        }

        return null;
    }
}
