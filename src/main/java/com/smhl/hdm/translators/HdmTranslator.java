package com.smhl.hdm.translators;

import com.smhl.hdm.models.entities.HdmEntity;

import java.util.Map;

/**
 * Translator interface for Hdm. Translators are classes used to convert a map of input parameters into the appropriate Java class for use within the service-layer
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmTranslator<E extends HdmEntity> {

    E translate(Map<String, Object> values);
}
