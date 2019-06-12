package com.smhl.hdm.resources.game;

import com.smhl.hdm.resources.HdmResource;
import com.smhl.hdm.resources.participant.impl.SkaterResource;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for Scoring plays
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class ScoringPlayResource implements HdmResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private TeamResource team;

    @Getter
    @Setter
    private SkaterResource scoringSkater;

    @Getter
    @Setter
    private SkaterResource primaryAssistingSkater;

    @Getter
    @Setter
    private SkaterResource secondaryAssistingSkater;


    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.team.isPresent() &&
                this.scoringSkater.isPresent() &&
                this.primaryAssistingSkater != null && this.primaryAssistingSkater.isPresent() &&
                this.secondaryAssistingSkater != null && this.secondaryAssistingSkater.isPresent();
    }
}
