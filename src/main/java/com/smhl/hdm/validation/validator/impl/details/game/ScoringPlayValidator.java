package com.smhl.hdm.validation.validator.impl.details.game;

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
 * Validation for scoring plays
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class ScoringPlayValidator extends AbstractHdmValidator implements HdmValidator {

    private SkaterService skaterService;
    private TeamService teamService;

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("team", "scoringSkater", "primaryAssistingSkater", "secondaryAssistingSkater");

    @Autowired
    public ScoringPlayValidator(SkaterService skaterService, TeamService teamService) {
        this.skaterService = skaterService;
        this.teamService = teamService;
    }

    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String teamId = values.get("team").toString();
        String scoringId = values.get("scoringSkater").toString();
        String primaryId = values.get("primaryAssistingSkater").toString();
        String secondaryId = values.get("secondaryAssistingSkater").toString();

        if (!super.isNumber(teamId) || !super.isNumber(scoringId) || !super.isNumber(primaryId) || !super.isNumber(secondaryId)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "One of the given values was not a number");
        }

        Long team = Long.parseLong(teamId);
        Long scoring = Long.parseLong(teamId);
        Long primary = Long.parseLong(teamId);
        Long secondary = Long.parseLong(teamId);

        if (super.isOverflow(team) || super.isOverflow(scoring) || super.isOverflow(primary) || super.isOverflow(secondary)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given id was too long");
        }

        if (scoring.equals(primary) || scoring.equals(secondary) || primary.equals(secondary)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "A participant cannot be involved more than once on a scoring play");
        }

        Optional<Team> t = this.teamService.find(team);

        if (t.isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given team does not exist in the system");
        }

        Optional<Skater> s = this.skaterService.find(scoring);
        Optional<Skater> p = this.skaterService.find(primary);
        Optional<Skater> sd = this.skaterService.find(secondary);

        if (s.isEmpty() || p.isEmpty() || sd.isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "One or more of the given skaters do not exist in the system");
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
