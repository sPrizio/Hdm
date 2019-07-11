package com.smhl.hdm.converters.game;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.converters.participant.impl.SkaterConverter;
import com.smhl.hdm.converters.participant.impl.TeamConverter;
import com.smhl.hdm.models.entities.game.ScoringPlay;
import com.smhl.hdm.resources.game.ScoringPlayResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Converter for scoring play resources. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class ScoringPlayConverter implements HdmConverter<ScoringPlay, ScoringPlayResource> {

    private SkaterConverter skaterConverter;
    private TeamConverter teamConverter;

    @Autowired
    public ScoringPlayConverter(SkaterConverter skaterConverter, TeamConverter teamConverter) {
        this.skaterConverter = skaterConverter;
        this.teamConverter = teamConverter;
    }


    //  METHODS

    @Override
    public ScoringPlayResource convert(ScoringPlay entity) {

        ScoringPlayResource resource = new ScoringPlayResource();

        if (Objects.nonNull(entity)) {
            resource.setCode(entity.getId());
            resource.setTeam(this.teamConverter.convert(entity.getTeam()));
            resource.setScoringSkater(this.skaterConverter.convert(entity.getScoringSkater()));

            //  since the assisting skaters can be null, we need to null check them
            if (Objects.nonNull(entity.getPrimaryAssistingSkater())) {
                resource.setPrimaryAssistingSkater(this.skaterConverter.convert(entity.getPrimaryAssistingSkater()));
            }

            if (Objects.nonNull(entity.getSecondaryAssistingSkater())) {
                resource.setSecondaryAssistingSkater(this.skaterConverter.convert(entity.getSecondaryAssistingSkater()));
            }
        }

        return resource;
    }

    @Override
    public List<ScoringPlayResource> convertAll(List<ScoringPlay> entity) {

        List<ScoringPlayResource> resources = new ArrayList<>();

        entity.forEach(scoringPlay -> resources.add(convert(scoringPlay)));

        return resources;
    }
}
