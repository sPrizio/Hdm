package com.smhl.hdm.resources.details.participant;

import com.smhl.hdm.resources.HdmResource;
import com.smhl.hdm.resources.participant.impl.SkaterResource;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A DTO for SkaterGameDetails
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class SkaterGameDetailsResource implements HdmResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private LocalDateTime gameTime;

    @Getter
    @Setter
    private SkaterResource skater;

    @Getter
    @Setter
    private TeamResource team;

    @Getter
    @Setter
    private int goals;

    @Getter
    @Setter
    private int assists;

    @Getter
    @Setter
    private int points;

    @Getter
    @Setter
    private int shots;

    @Getter
    @Setter
    private int blockedShots;


    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.gameTime != null &&
                this.skater.isPresent() &&
                this.team.isPresent();
    }
}
