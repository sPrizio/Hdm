package com.smhl.hdm.translators.game;

import com.smhl.hdm.models.entities.game.ScoringPlay;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.service.entities.participant.impl.SkaterService;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.translators.HdmTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Implementation of a translator for ScoringPlay. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
@SuppressWarnings("OptionalIsPresent")
public class ScoringPlayTranslator implements HdmTranslator<ScoringPlay> {

    private SkaterService skaterService;
    private TeamService teamService;

    @Autowired
    public ScoringPlayTranslator(SkaterService skaterService, TeamService teamService) {
        this.skaterService = skaterService;
        this.teamService = teamService;
    }


    //  METHODS

    @Override
    public ScoringPlay translate(Map<String, Object> values) {

        Optional<Skater> scorer = this.skaterService.find(Long.parseLong(values.get("scoringSkater").toString()));
        Optional<Skater> primary = this.skaterService.find(Long.parseLong(values.get("primaryAssistingSkater").toString()));
        Optional<Skater> secondary = this.skaterService.find(Long.parseLong(values.get("secondaryAssistingSkater").toString()));
        Optional<Team> team = this.teamService.find(Long.parseLong(values.get("team").toString()));

        if (scorer.isPresent() && team.isPresent()) {
            return new ScoringPlay(
                    team.get(),
                    scorer.get(),
                    primary.isPresent() ? primary.get() : null,
                    secondary.isPresent() ? secondary.get() : null
            );
        }

        return null;
    }
}
