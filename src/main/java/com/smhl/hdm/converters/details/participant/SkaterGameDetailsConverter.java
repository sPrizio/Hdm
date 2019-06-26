package com.smhl.hdm.converters.details.participant;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.converters.participant.impl.SkaterConverter;
import com.smhl.hdm.converters.participant.impl.TeamConverter;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.resources.details.participant.SkaterGameDetailsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Converter for skater game details resources. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterGameDetailsConverter implements HdmConverter<SkaterGameDetails, SkaterGameDetailsResource> {

    private SkaterConverter skaterConverter;
    private TeamConverter teamConverter;

    @Autowired
    public SkaterGameDetailsConverter(SkaterConverter skaterConverter, TeamConverter teamConverter) {
        this.skaterConverter = skaterConverter;
        this.teamConverter = teamConverter;
    }


    //  METHODS

    @Override
    public SkaterGameDetailsResource convert(SkaterGameDetails entity) {

        SkaterGameDetailsResource resource = new SkaterGameDetailsResource();

        if (Objects.nonNull(entity)) {
            resource.setCode(entity.getId());
            resource.setGameTime(entity.getGameTime());
            resource.setSkater(this.skaterConverter.convert(entity.getParticipant()));
            resource.setTeam(this.teamConverter.convert(entity.getTeam()));
            resource.setGoals(entity.getGoals());
            resource.setAssists(entity.getAssists());
            resource.setPoints((entity.getGoals() + entity.getAssists()));
            resource.setShots(entity.getShots());
            resource.setBlockedShots(entity.getBlockedShots());
        }

        return resource;
    }

    @Override
    public List<SkaterGameDetailsResource> convertAll(List<SkaterGameDetails> entity) {

        List<SkaterGameDetailsResource> resources = new ArrayList<>();

        entity.forEach(skaterGameDetails -> resources.add(convert(skaterGameDetails)));

        return resources;
    }
}
