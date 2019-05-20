package com.smhl.hdm.converters;

import com.smhl.hdm.models.HdmEntity;
import com.smhl.hdm.resources.HdmResource;

import java.util.List;
import java.util.Set;

/**
 * A converter is a class that converts entities into dtos for use by API controllers
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmConverter<E extends HdmEntity, R extends HdmResource> {

    R convert(E entity);

    List<R> convertAll(Set<E> entity);
}
