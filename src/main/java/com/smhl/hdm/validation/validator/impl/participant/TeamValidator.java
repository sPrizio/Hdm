package com.smhl.hdm.validation.validator.impl.participant;

import com.smhl.hdm.enums.ValidationResponseResult;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.HdmValidator;
import com.smhl.hdm.validation.validator.impl.AbstractHdmValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * * Implementation of a validator for goalies. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class TeamValidator extends AbstractHdmValidator implements HdmValidator {

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("name", "active");

    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String name = values.get("name").toString();
        String active = values.get("active").toString();

        if (super.isTooLarge(name) || super.isTooLarge(active)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Name and/or active have a length greater than 1000");
        }

        if (super.hasUnacceptableSymbol(name) || super.hasUnacceptableSymbol(active)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Name and/or active contained an unacceptable symbol");
        }

        if (super.hasCriticalWord(name) || super.hasCriticalWord(active)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Possible SQL injection detected, failing immediately!");
        }

        if (!StringUtils.isAlpha(name)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "A team name cannot contain numbers");
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
