package com.smhl.hdm.translators.game;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.translators.HdmTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Implementation of a translator for Game. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameTranslator implements HdmTranslator<Game> {

    private TeamService teamService;

    @Autowired
    public GameTranslator(TeamService teamService) {
        this.teamService = teamService;
    }

    //  METHODS

    @Override
    public Game translate(Map<String, Object> values) {

        Optional<Team> homeTeam = this.teamService.find(Long.parseLong(values.get("homeTeam").toString()));
        Optional<Team> awayTeam = this.teamService.find(Long.parseLong(values.get("awayTeam").toString()));

        if (homeTeam.isPresent() && awayTeam.isPresent()) {
            return new Game(
                    HdmUtils.parse(values.get("gameTime").toString(), CoreConstants.DATE_FORMAT_LONG),
                    values.get("seasonString").toString(),
                    values.get("gameStatus").toString(),
                    homeTeam.get(),
                    awayTeam.get()
            );
        }

        return null;
    }
}
