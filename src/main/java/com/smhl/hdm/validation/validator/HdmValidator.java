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

    /**
     * Validates a map of parameters used for entity operations. We return a validation result as a consequence of computation
     *
     * @param values insertion params
     * @return a validation result based on the state of the insertion params
     */
    ValidationResult validate(Map<String, Object> values);
}
