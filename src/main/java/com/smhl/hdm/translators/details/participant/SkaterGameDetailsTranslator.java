package com.smhl.hdm.translators.details.participant;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.service.entities.participant.impl.SkaterService;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.translators.HdmTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Implementation of a translator for SkaterGameDetails. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterGameDetailsTranslator implements HdmTranslator<SkaterGameDetails> {

    private SkaterService skaterService;
    private TeamService teamService;

    @Autowired
    public SkaterGameDetailsTranslator(SkaterService skaterService, TeamService teamService) {
        this.skaterService = skaterService;
        this.teamService = teamService;
    }


    //  METHODS

    @Override
    public SkaterGameDetails translate(Map<String, Object> values) {

        Optional<Skater> skater = this.skaterService.find(Long.parseLong(values.get("participant").toString()));
        Optional<Team> team = this.teamService.find(Long.parseLong(values.get("team").toString()));

        if (skater.isPresent() && team.isPresent()) {
            return new SkaterGameDetails(
                    HdmUtils.parse(values.get("gameTime").toString(), CoreConstants.DATE_FORMAT_LONG),
                    skater.get(),
                    team.get(),
                    Integer.parseInt(values.get("goals").toString()),
                    Integer.parseInt(values.get("assists").toString()),
                    Integer.parseInt(values.get("shots").toString()),
                    Integer.parseInt(values.get("blockedShots").toString())
            );
        }

        return null;
    }
}
