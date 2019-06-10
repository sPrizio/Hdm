package com.smhl.hdm.resources.details.participant;

import com.smhl.hdm.resources.HdmResource;
import com.smhl.hdm.resources.participant.impl.GoalieResource;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * A DTO for GoalieGameDetails
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class GoalieGameDetailsResource implements HdmResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private LocalDateTime gameTime;

    @Getter
    @Setter
    private GoalieResource goalie;

    @Getter
    @Setter
    private TeamResource team;

    @Getter
    @Setter
    private boolean isStarter;

    @Getter
    @Setter
    private String gameResult;

    @Getter
    @Setter
    private int shotsAgainst;

    @Getter
    @Setter
    private int saves;

    @Getter
    @Setter
    private int goalsAgainst;


    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.gameTime != null &&
                this.goalie.isPresent() &&
                this.team.isPresent() &&
                StringUtils.isNotEmpty(gameResult);
    }
}
