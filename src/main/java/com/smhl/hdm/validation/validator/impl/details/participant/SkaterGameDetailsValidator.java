package com.smhl.hdm.validation.validator.impl.details.participant;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.enums.ValidationResponseResult;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.service.entities.participant.impl.SkaterService;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.HdmValidator;
import com.smhl.hdm.validation.validator.impl.AbstractHdmValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of a validator for skater game details
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterGameDetailsValidator extends AbstractHdmValidator implements HdmValidator {

    private SkaterService skaterService;
    private TeamService teamService;

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("gameTime", "participant", "team", "goals", "assists", "shots", "blockedShots");

    @Autowired
    public SkaterGameDetailsValidator(SkaterService skaterService, TeamService teamService) {
        this.skaterService = skaterService;
        this.teamService = teamService;
    }


    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String date = values.get("gameTime").toString();
        Object p = values.get("participant");
        String participant = p.toString();
        String team = values.get("team").toString();
        String goals = values.get("goals").toString();
        String assists = values.get("assists").toString();
        String shots = values.get("shots").toString();
        String blockedShots = values.get("blockedShots").toString();

        //  game time
        if (super.isInvalidDateForFormat(date, CoreConstants.DATE_FORMAT_LONG)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Invalid date format. Date must be of the format 'yyyy-MM-dd HH:mm:ss'");
        }

        if (!super.isNumber(participant) || !super.isNumber(team)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Skater/Team value was not a valid number");
        }

        Long skater = Long.parseLong(participant);
        Long t = Long.parseLong(team);

        if (super.isOverflow(skater) || super.isOverflow(t)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given skater/team id was too long");
        }

        Optional<Skater> s = this.skaterService.find(skater);
        Optional<Team> tt = this.teamService.find(t);

        if (s.isEmpty() || tt.isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given skater/team does not exist in the system");
        }

        if (!super.isNumber(goals) || !super.isNumber(assists) || !super.isNumber(shots) || !super.isNumber(blockedShots)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "g, a, s and/or bs were not valid numbers");

        }

        Integer g = Integer.parseInt(goals);
        Integer a = Integer.parseInt(assists);
        Integer sh = Integer.parseInt(shots);
        Integer bs = Integer.parseInt(blockedShots);

        if (super.isTooLarge(g) || super.isTooLarge(a) || super.isTooLarge(sh) || super.isTooLarge(bs)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "g, a, s and/or bs were were too large");
        }

        if (super.isNegative(g) || super.isNegative(a) || super.isNegative(sh) || super.isNegative(bs)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "g, a, s and/or bs were negative. Negative values are not allowed");
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
