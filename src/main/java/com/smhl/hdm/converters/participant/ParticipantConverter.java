package com.smhl.hdm.converters.participant;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.resources.HdmResource;

import java.util.List;

/**
 * Participants follow their own converter pattern since we have season-specific functionality that needs to be encapsulated.
 * Participant resources have a 'season' attribute that can be populated for a specific season, as such, we need to represent this
 * functionality separately from the other converters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface ParticipantConverter<E extends Participant, R extends HdmResource> extends HdmConverter<E, R> {

    /**
     * Converts a resource for a given season. In this case, the resource's 'season' attribute will be populated with the entity's
     * season values for the matching season
     *
     * @param season season we wish to populate
     * @param entity entity to be converter
     * @return resource with the associated entity values
     */
    R convertForSeason(String season, E entity);

    /**
     * Converts a list of entities into a list of resources for the given season
     *
     * @param season season we wish to populate
     * @param entity entities to be converted to resources
     * @return list of resources matching their respective entities
     */
    List<R> convertAllForSeason(String season, List<E> entity);
}
