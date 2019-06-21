package com.smhl.hdm.resources.details.participant;

import com.smhl.hdm.resources.HdmResource;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * A DTO for TeamGameDetails
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class TeamGameDetailsResource implements HdmResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private LocalDateTime gameTime;

    @Getter
    @Setter
    private TeamResource team;

    @Getter
    @Setter
    private String gameResult;

    @Getter
    @Setter
    private Integer goalsFor;

    @Getter
    @Setter
    private Integer goalsAgainst;


    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.gameTime != null &&
                this.team.isPresent() &&
                StringUtils.isNotEmpty(gameResult);
    }
}
