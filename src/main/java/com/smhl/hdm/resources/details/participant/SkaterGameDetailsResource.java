package com.smhl.hdm.resources.details.participant;

import com.smhl.hdm.resources.HdmResource;
import com.smhl.hdm.resources.participant.impl.SkaterResource;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private Integer goals;

    @Getter
    @Setter
    private Integer assists;

    @Getter
    @Setter
    private Integer points;

    @Getter
    @Setter
    private Integer shots;

    @Getter
    @Setter
    private Integer blockedShots;


    //  METHODS

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SkaterGameDetailsResource resource = (SkaterGameDetailsResource) o;
        return this.code.equals(resource.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                this.gameTime != null &&
                this.skater.isPresent() &&
                this.team.isPresent();
    }


}
