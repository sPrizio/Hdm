package com.smhl.hdm.resources.participant.impl;

import com.smhl.hdm.resources.participant.ParticipantResource;
import com.smhl.hdm.resources.season.TeamSeasonResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * DTO class for teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class TeamResource implements ParticipantResource {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private TeamSeasonResource season;

    @Getter
    @Setter
    private Set<TeamSeasonResource> seasons;

    @Override
    public boolean isPresent() {
        return StringUtils.isNotEmpty(this.name) &&
                this.season.isPresent() &&
                CollectionUtils.isNotEmpty(this.seasons);
    }
}
