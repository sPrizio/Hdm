package com.smhl.hdm.converters;

import com.smhl.hdm.models.entities.HdmEntity;
import com.smhl.hdm.resources.HdmResource;

import java.util.List;

/**
 * A converter is a class that converts entities into dtos for use by API controllers
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmConverter<E extends HdmEntity, R extends HdmResource> {

    R convert(E entity);

    List<R> convertAll(List<E> entity);
}
