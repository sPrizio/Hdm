package com.smhl.hdm.validation.validator;

import com.smhl.hdm.validation.result.ValidationResult;

import java.util.Map;

/**
 * Validation blueprint for all validators in the system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmValidator {

    ValidationResult validate(Map<String, Object> values);
}
