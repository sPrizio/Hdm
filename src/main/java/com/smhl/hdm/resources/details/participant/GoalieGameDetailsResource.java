package com.smhl.hdm.resources.details.participant;

import com.smhl.hdm.resources.HdmResource;
import com.smhl.hdm.resources.participant.impl.GoalieResource;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private Boolean isStarter;

    @Getter
    @Setter
    private String gameResult;

    @Getter
    @Setter
    private Integer shotsAgainst;

    @Getter
    @Setter
    private Integer saves;

    @Getter
    @Setter
    private Integer goalsAgainst;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.gameTime != null &&
                this.goalie.isPresent() &&
                this.team.isPresent() &&
                StringUtils.isNotEmpty(gameResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GoalieGameDetailsResource resource = (GoalieGameDetailsResource) o;
        return this.code.equals(resource.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }
}
