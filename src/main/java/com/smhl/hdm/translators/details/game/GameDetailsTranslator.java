package com.smhl.hdm.translators.details.game;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.details.game.GameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.TeamGameDetails;
import com.smhl.hdm.models.entities.game.ScoringPlay;
import com.smhl.hdm.translators.HdmTranslator;
import com.smhl.hdm.translators.details.participant.GoalieGameDetailsTranslator;
import com.smhl.hdm.translators.details.participant.SkaterGameDetailsTranslator;
import com.smhl.hdm.translators.details.participant.TeamGameDetailsTranslator;
import com.smhl.hdm.translators.game.ScoringPlayTranslator;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Implementation of a translator for GameDetails. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
@SuppressWarnings({"Duplicates", "unchecked"})
public class GameDetailsTranslator implements HdmTranslator<GameDetails> {

    private SkaterGameDetailsTranslator skaterGameDetailsTranslator;
    private GoalieGameDetailsTranslator goalieGameDetailsTranslator;
    private TeamGameDetailsTranslator teamGameDetailsTranslator;
    private ScoringPlayTranslator scoringPlayTranslator;

    @Autowired
    public GameDetailsTranslator(SkaterGameDetailsTranslator skaterGameDetailsTranslator, GoalieGameDetailsTranslator goalieGameDetailsTranslator, TeamGameDetailsTranslator teamGameDetailsTranslator, ScoringPlayTranslator scoringPlayTranslator) {
        this.skaterGameDetailsTranslator = skaterGameDetailsTranslator;
        this.goalieGameDetailsTranslator = goalieGameDetailsTranslator;
        this.teamGameDetailsTranslator = teamGameDetailsTranslator;
        this.scoringPlayTranslator = scoringPlayTranslator;
    }


    //  METHODS

    @Override
    public GameDetails translate(Map<String, Object> values) {

        Set<SkaterGameDetails> skaterGameDetails = new HashSet<>();
        Set<GoalieGameDetails> goalieGameDetails = new HashSet<>();
        Set<TeamGameDetails> teamGameDetails = new HashSet<>();
        Set<ScoringPlay> scoringPlays = new HashSet<>();

        List<Map<String, Object>> s = (List<Map<String, Object>>) values.get("skaterGameDetails");
        List<Map<String, Object>> g = (List<Map<String, Object>>) values.get("goalieGameDetails");
        List<Map<String, Object>> t = (List<Map<String, Object>>) values.get("teamGameDetails");
        List<Map<String, Object>> sP = (List<Map<String, Object>>) values.get("scoringPlays");


        //  create the sets of game details using the translators
        s.forEach(
                map -> skaterGameDetails.add(this.skaterGameDetailsTranslator.translate(map))
        );

        g.forEach(
                map -> goalieGameDetails.add(this.goalieGameDetailsTranslator.translate(map))
        );

        t.forEach(
                map -> teamGameDetails.add(this.teamGameDetailsTranslator.translate(map))
        );

        sP.forEach(
                map -> scoringPlays.add(this.scoringPlayTranslator.translate(map))
        );

        //  if any of the sets contain nulls, it means one of the entries failed and thus we fail the entire process
        boolean badSkater = skaterGameDetails.stream().anyMatch(Objects::isNull);
        boolean badGoalie = goalieGameDetails.stream().anyMatch(Objects::isNull);
        boolean badTeam = teamGameDetails.stream().anyMatch(Objects::isNull);
        boolean badSP = scoringPlays.stream().anyMatch(Objects::isNull);

        if (badSkater || badGoalie || badTeam || badSP) {
            return null;
        }

        return new GameDetails(
                HdmUtils.parse(values.get("gameTime").toString(), CoreConstants.DATE_FORMAT_LONG),
                Integer.parseInt(values.get("homeTeamScore").toString()),
                Integer.parseInt(values.get("awayTeamScore").toString()),
                skaterGameDetails,
                goalieGameDetails,
                teamGameDetails,
                scoringPlays
        );
    }
}
