package com.smhl.hdm.validation.validator.impl.details.participant;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.enums.GameResult;
import com.smhl.hdm.enums.ValidationResponseResult;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.HdmValidator;
import com.smhl.hdm.validation.validator.impl.AbstractHdmValidator;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Validation for team game details
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class TeamGameDetailsValidator extends AbstractHdmValidator implements HdmValidator {

    private TeamService teamService;

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("gameTime", "participant", "gameResult", "goalsFor", "goalsAgainst");

    @Autowired
    public TeamGameDetailsValidator(TeamService teamService) {
        this.teamService = teamService;
    }


    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String date = values.get("gameTime").toString();
        String participant = values.get("participant").toString();
        String result = values.get("gameResult").toString();
        String goalsFor = values.get("goalsFor").toString();
        String goalsAgainst = values.get("goalsAgainst").toString();

        //  game time
        if (super.isInvalidDateForFormat(date, CoreConstants.DATE_FORMAT_LONG)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Invalid date format. Date must be of the format 'yyyy-MM-dd HH:mm:ss'");
        }

        if (!super.isNumber(participant)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Team value was not a valid number");
        }

        Long team = Long.parseLong(participant);

        if (super.isOverflow(team)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given team id was too long");
        }

        Optional<Team> t = this.teamService.find(team);

        if (t.isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given team does not exist in the system");
        }

        if (!EnumUtils.isValidEnum(GameResult.class, result)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given status was not a valid option. Valid options are: " + Arrays.stream(GameResult.values()).collect(Collectors.toList()));
        }

        if (!super.isNumber(goalsFor) || !super.isNumber(goalsAgainst)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Goals for and/or goals against was not a valid number");
        }

        Integer gF = Integer.parseInt(goalsFor);
        Integer gA = Integer.parseInt(goalsAgainst);

        if (super.isTooLarge(gF) || super.isTooLarge(gA)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Goals for and/or goals against was too large");
        }

        if (super.isNegative(gF) || super.isNegative(gA)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Negative inputs are not allowed");
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
