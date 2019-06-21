package com.smhl.hdm.resources.details.game;

import com.smhl.hdm.resources.HdmResource;
import com.smhl.hdm.resources.details.participant.GoalieGameDetailsResource;
import com.smhl.hdm.resources.details.participant.SkaterGameDetailsResource;
import com.smhl.hdm.resources.details.participant.TeamGameDetailsResource;
import com.smhl.hdm.resources.game.ScoringPlayResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * A DTO for GameDetails
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 */
@NoArgsConstructor
public class GameDetailsResource implements HdmResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private LocalDateTime gameTime;

    @Getter
    @Setter
    private Integer homeTeamScore;

    @Getter
    @Setter
    private Integer awayTeamScore;

    @Getter
    @Setter
    private Set<SkaterGameDetailsResource> skaterGameDetails;

    @Getter
    @Setter
    private Set<GoalieGameDetailsResource> goalieGameDetails;

    @Getter
    @Setter
    private Set<TeamGameDetailsResource> teamGameDetails;

    @Getter
    @Setter
    private Set<ScoringPlayResource> scoringPlays;


    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.gameTime != null &&
                CollectionUtils.isNotEmpty(this.skaterGameDetails) &&
                CollectionUtils.isNotEmpty(this.goalieGameDetails) &&
                CollectionUtils.isNotEmpty(this.teamGameDetails) &&
                CollectionUtils.isNotEmpty(this.scoringPlays);
    }
}
