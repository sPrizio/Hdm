package com.smhl.hdm.resources.participant.impl;

import com.smhl.hdm.resources.participant.ParticipantResource;
import com.smhl.hdm.resources.season.GoalieSeasonResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * DTO class for goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class GoalieResource implements ParticipantResource {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String position;

    @Getter
    @Setter
    private GoalieSeasonResource season;

    @Getter
    @Setter
    private Set<GoalieSeasonResource> seasons;

    @Override
    public boolean isPresent() {
        return StringUtils.isNotEmpty(this.name) &&
                StringUtils.isNotEmpty(this.position) &&
                this.season.isPresent() &&
                CollectionUtils.isNotEmpty(this.seasons);
    }
}
