package com.smhl.hdm.converters.season;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.models.entities.season.impl.SkaterSeason;
import com.smhl.hdm.resources.season.SkaterSeasonResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Converter for skater season resources
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterSeasonConverter implements HdmConverter<SkaterSeason, SkaterSeasonResource> {

    @Override
    public SkaterSeasonResource convert(SkaterSeason entity) {

        SkaterSeasonResource resource = new SkaterSeasonResource();

        if (Objects.nonNull(entity)) {
            resource.setSeasonString(entity.getSeasonString());
            resource.setGamesPlayed(entity.getGamesPlayed());
            resource.setGoals(entity.getGoals());
            resource.setAssists(entity.getAssists());
            resource.setPoints(entity.getPoints());
            resource.setPointsPerGame(entity.getPointsPerGame());
            resource.setShots(entity.getShots());
            resource.setBlockedShots(entity.getBlockedShots());
        }

        return resource;
    }

    @Override
    public List<SkaterSeasonResource> convertAll(List<SkaterSeason> entity) {

        List<SkaterSeasonResource> resources = new ArrayList<>();

        entity.forEach(skaterSeason -> resources.add(convert(skaterSeason)));

        return resources;
    }
}
