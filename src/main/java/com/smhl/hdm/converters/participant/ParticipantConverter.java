package com.smhl.hdm.converters.participant;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.resources.HdmResource;

import java.util.List;

/**
 * Converter with specific methods for participants
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface ParticipantConverter<E extends Participant, R extends HdmResource> extends HdmConverter<E, R> {

    R convertForSeason(String season, E entity);

    List<R> convertAllForSeason(String season, List<E> entity);
}
