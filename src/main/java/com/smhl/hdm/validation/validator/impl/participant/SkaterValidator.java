package com.smhl.hdm.validation.validator.impl.participant;

import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.HdmValidator;
import com.smhl.hdm.validation.validator.impl.AbstractHdmValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Implementation of a validator for skaters. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterValidator extends AbstractHdmValidator implements HdmValidator {

    @Override
    public ValidationResult validate(Map<String, Object> values) {
        return super.validateSkaterOrGoalie(values, SkaterValidator.class);
    }
}
