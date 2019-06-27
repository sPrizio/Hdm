package com.smhl.hdm.validation.validator.impl.details.participant;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.enums.GameResult;
import com.smhl.hdm.enums.ValidationResponseResult;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.service.entities.participant.impl.GoalieService;
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
 * Implementation of goalie game details validation. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GoalieGameDetailsValidator extends AbstractHdmValidator implements HdmValidator {

    private GoalieService goalieService;
    private TeamService teamService;

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("gameTime", "participant", "team", "isStarter", "gameResult", "shotsAgainst", "saves", "goalsAgainst");

    @Autowired
    public GoalieGameDetailsValidator(GoalieService goalieService, TeamService teamService) {
        this.goalieService = goalieService;
        this.teamService = teamService;
    }


    //  METHODS

    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String date = values.get("gameTime").toString();
        String participant = values.get("participant").toString();
        String team = values.get("team").toString();
        String starter = values.get("isStarter").toString();
        String result = values.get("gameResult").toString();
        String shotsAgainst = values.get("shotsAgainst").toString();
        String saves = values.get("saves").toString();
        String goalsAgainst = values.get("goalsAgainst").toString();

        //  game time
        if (super.isInvalidDateForFormat(date, CoreConstants.DATE_FORMAT_LONG)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Invalid date format. Date must be of the format 'yyyy-MM-dd HH:mm:ss'");
        }

        if (!super.isNumber(participant) || !super.isNumber(team)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Goalie/Team value was not a valid number");
        }

        Long goalie = Long.parseLong(participant);
        Long t = Long.parseLong(team);

        if (super.isOverflow(goalie) || super.isOverflow(t)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given goalie/team id was too long");
        }

        Optional<Goalie> g = this.goalieService.find(goalie);
        Optional<Team> tt = this.teamService.find(t);

        if (g.isEmpty() || tt.isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given goalie/team does not exist in the system");
        }

        if (!starter.equals("yes") && !starter.equals("no")) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Starter param only accepts yes/no values");
        }

        if (!EnumUtils.isValidEnum(GameResult.class, result)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given status was not a valid option. Valid options are: " + Arrays.stream(GameResult.values()).collect(Collectors.toList()));
        }

        if (!super.isNumber(shotsAgainst) || !super.isNumber(saves) || !super.isNumber(goalsAgainst)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "SA, Svs and/or GA were not valid numbers");
        }

        Integer sA = Integer.parseInt(shotsAgainst);
        Integer svs = Integer.parseInt(saves);
        Integer gA = Integer.parseInt(goalsAgainst);

        if (super.isTooLarge(sA) || super.isTooLarge(svs) || super.isTooLarge(gA)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "SA, Svs and/or GA were were too large");
        }

        if (super.isNegative(sA) || super.isNegative(svs) || super.isNegative(gA)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "SA, Svs and/or GA were negative. Negative values are not allowed");
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
