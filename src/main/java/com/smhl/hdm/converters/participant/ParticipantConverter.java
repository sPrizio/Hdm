package com.smhl.hdm.converters.participant;

import com.smhl.hdm.converters.HdmConverter;
import com.smhl.hdm.models.participant.Participant;
import com.smhl.hdm.resources.HdmResource;

import java.util.List;
import java.util.Set;

/**
 * Converter with specific methods for participants
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface ParticipantConverter<E extends Participant, R extends HdmResource> extends HdmConverter<E, R> {

    R convertForSeason(String season, E entity);

    List<R> convertAllForSeason(String season, Set<E> entity);
}
