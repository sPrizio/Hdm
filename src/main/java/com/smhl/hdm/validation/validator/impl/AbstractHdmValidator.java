package com.smhl.hdm.validation.validator.impl;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.enums.ParticipantPosition;
import com.smhl.hdm.enums.ValidationResponseResult;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.impl.participant.SkaterValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Collection of methods that all children will use for validation services
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public abstract class AbstractHdmValidator {

    private static final String[] CRITICAL_WORDS = {"CREATE", "DROP", "INSERT", "TABLE", "ALTER", "DELETE", "FROM", "UPDATE"};
    private static final String[] UNACCEPTABLE_SYMBOLS = {"=", "+", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "/", "\\", "|", "[", "]", "{", "}", "~", ".", ",", ":", ";"};


    //  METHODS

    /**
     * Checks for rejected symbols
     *
     * @param s string to test
     * @return true if any symbol is detected, false otherwise
     */
    protected boolean hasUnacceptableSymbol(String s) {

        List<String> tokens = convertStringToTokens(s);

        for (String string : UNACCEPTABLE_SYMBOLS) {
            for (String token : tokens) {
                if (token.contains(string)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks for certain critical words that would be maliciously abused
     *
     * @param s string to test
     * @return true if any critical word is detected, false otherwise
     */
    protected boolean hasCriticalWord(String s) {

        List<String> tokens = convertStringToTokens(s);

        for (String string : CRITICAL_WORDS) {
            String st = string.toLowerCase();
            if (tokens.contains(st)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a string is a digit
     *
     * @param s - given string
     * @return true if string is a number, false otherwise
     */
    protected boolean isNumber(String s) {
        return NumberUtils.isNumber(s);
    }

    /**
     * Checks if a passed integer is negative
     *
     * @param integer - passed integer
     * @return true if integer is less than 0
     */
    protected boolean isNegative(Integer integer) {
        return integer < 0;
    }

    /**
     * Checks if a passed string is too large
     *
     * @param s - passed string
     * @return true if string is of length 1001 or greater
     */
    protected boolean isTooLarge(String s) {
        return s.length() > 1000;
    }

    /**
     * Checks if a passed integer is too large
     *
     * @param integer - passed string
     * @return true if the integer is greater than 1000
     */
    protected boolean isTooLarge(Integer integer) {
        return integer > 1000;
    }

    /**
     * Checks if a passed Long is overflowing
     *
     * @param num - passed string
     * @return true if Long is overflowing
     */
    protected boolean isOverflow(Long num) {
        return num > 1000000000;
    }

    /**
     * Checks if a passed long is negative
     *
     * @param num - passed integer
     * @return true if integer is less than 0
     */
    protected boolean isNegative(Long num) {
        return num < 0;
    }

    /**
     * Checks for valid date formats according to the given format
     *
     * @param s date that we're checking
     * @param format desired date format (ex: YYYY-DD-MM)
     * @return true if the value matches the given format
     */
    protected boolean isInvalidDateForFormat(String s, String format) {

        if (isTooLarge(s) || hasCriticalWord(s)) {
            return false;
        }

        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, CoreConstants.HDM_LOCALE);

        try {
            dateTime = LocalDateTime.parse(s, formatter);
            String result = dateTime.format(formatter);
            return !result.equals(s);
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(s, formatter);
                String result = date.format(formatter);
                return !result.equals(s);
            } catch (DateTimeParseException ex) {
                return true;
            }
        }
    }

    /**
     * Determines if any params are missing and if so, returns a string demarcating which ones are missing
     *
     * @param params map of request parameters
     * @param desiredParams parameters required for this request
     * @return a string containing the missing params or null if none are missing
     */
    protected String isMissingParam(Map<String, Object> params, List<String> desiredParams) {

        List<String> nonNullParams = desiredParams
                .stream()
                .filter(param -> params.get(param) != null)
                .collect(Collectors.toList());

        if (nonNullParams.size() != desiredParams.size()) {
            List<String> subtractedList = (List<String>) CollectionUtils.subtract(desiredParams, nonNullParams);
            return "Params " + subtractedList.toString() + " were not found in the request. Please include them";
        }

        return StringUtils.EMPTY;
    }

    /**
     * Validation logic for skaters and goalies since they're the same
     *
     * @param values request parameters
     * @return a validation result as a result of verifying the request params
     */
    protected ValidationResult validateSkaterOrGoalie(Map<String, Object> values, Class clazz) {

        List<String> expectedParams = Arrays.asList("firstName", "lastName", "position", "active");

        //  check for missing params
        if (StringUtils.isNotEmpty(isMissingParam(values, expectedParams))) {
            return new ValidationResult(ValidationResponseResult.FAILED, isMissingParam(values, expectedParams));
        }

        String firstName = values.get("firstName").toString();
        String lastName = values.get("lastName").toString();
        String position = values.get("position").toString();
        String active = values.get("active").toString();

        if (isTooLarge(firstName) || isTooLarge(lastName) || isTooLarge(position) || isTooLarge(active)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "One of the given string values had a length greater than 1000");
        }

        if (hasUnacceptableSymbol(firstName) || hasUnacceptableSymbol(lastName) || hasUnacceptableSymbol(position) || hasUnacceptableSymbol(active)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "One of the given string values had an unacceptable symbol");
        }

        if (hasCriticalWord(firstName) || hasCriticalWord(lastName) || hasCriticalWord(position) || hasCriticalWord(active)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Possible SQL injection detected, failing request immediately!");
        }

        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Name fields cannot contain numbers");
        }

        if (!EnumUtils.isValidEnum(ParticipantPosition.class, position)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given position was not a valid option. Valid options are: " + Arrays.stream(ParticipantPosition.values()).collect(Collectors.toList()));
        }

        //  check for a goalie position for a skater
        if (clazz.equals(SkaterValidator.class) && ParticipantPosition.valueOf(position).equals(ParticipantPosition.GOALIE)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "A skater cannot be a goalie");
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }


    //  HELPERS

    /**
     * Converts a string to a list of its tokens
     *
     * @param string string to be broken up
     * @return list of string tokens
     */
    private List<String> convertStringToTokens(String string) {

        StringTokenizer stringTokenizer = new StringTokenizer(string);
        List<String> tokens = new ArrayList<>();

        while (stringTokenizer.hasMoreTokens()) {
            tokens.add(stringTokenizer.nextToken());
        }

        return tokens;
    }
}
