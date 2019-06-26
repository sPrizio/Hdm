package com.smhl.hdm.validation.validator.impl.details.game;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.enums.ValidationResponseResult;
import com.smhl.hdm.service.entities.game.GameService;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.HdmValidator;
import com.smhl.hdm.validation.validator.impl.AbstractHdmValidator;
import com.smhl.hdm.validation.validator.impl.details.participant.GoalieGameDetailsValidator;
import com.smhl.hdm.validation.validator.impl.details.participant.SkaterGameDetailsValidator;
import com.smhl.hdm.validation.validator.impl.details.participant.TeamGameDetailsValidator;
import com.smhl.hdm.validation.validator.impl.game.ScoringPlayValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Implementation of validation for game details
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
@SuppressWarnings({"unchecked", "Duplicates"})
public class GameDetailsValidator extends AbstractHdmValidator implements HdmValidator {

    private GameService gameService;
    private SkaterGameDetailsValidator skaterGameDetailsValidator;
    private GoalieGameDetailsValidator goalieGameDetailsValidator;
    private TeamGameDetailsValidator teamGameDetailsValidator;
    private ScoringPlayValidator scoringPlayValidator;

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("gameTime", "homeTeamScore", "awayTeamScore", "skaterGameDetails", "goalieGameDetails", "teamGameDetails", "scoringPlays");

    @Autowired
    public GameDetailsValidator(GameService gameService, SkaterGameDetailsValidator skaterGameDetailsValidator, GoalieGameDetailsValidator goalieGameDetailsValidator, TeamGameDetailsValidator teamGameDetailsValidator, ScoringPlayValidator scoringPlayValidator) {
        this.gameService = gameService;
        this.skaterGameDetailsValidator = skaterGameDetailsValidator;
        this.goalieGameDetailsValidator = goalieGameDetailsValidator;
        this.teamGameDetailsValidator = teamGameDetailsValidator;
        this.scoringPlayValidator = scoringPlayValidator;
    }


    @Override
    public ValidationResult validate(Map<String, Object> values) {

        //  check for missing params
        if (StringUtils.isNotEmpty(super.isMissingParam(values, EXPECTED_PARAMS))) {
            return new ValidationResult(ValidationResponseResult.FAILED, super.isMissingParam(values, EXPECTED_PARAMS));
        }

        String date = values.get("gameTime").toString();
        String home = values.get("homeTeamScore").toString();
        String away = values.get("awayTeamScore").toString();
        Map<String, Object> skaters = (Map<String, Object>) values.get("skaterGameDetails");
        Map<String, Object> goalies = (Map<String, Object>) values.get("goalieGameDetails");
        Map<String, Object> teams = (Map<String, Object>) values.get("teamGameDetails");
        Map<String, Object> scoringPlays = (Map<String, Object>) values.get("scoringPlays");

        //  game time
        if (super.isInvalidDateForFormat(date, CoreConstants.DATE_FORMAT_LONG)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Invalid date format. Date must be of the format 'yyyy-MM-dd HH:mm:ss'");
        }

        if (this.gameService.findByGameTime(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(CoreConstants.DATE_FORMAT_LONG, CoreConstants.HDM_LOCALE))) != null) {
            return new ValidationResult(ValidationResponseResult.FAILED, "A game with that game time already exists");
        }

        //  home team & away team
        if (!super.isNumber(home) || !super.isNumber(away)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "One or both of the score values was not a valid number");
        }

        Long h = Long.parseLong(home);
        Long a = Long.parseLong(away);

        if (super.isOverflow(h) || super.isOverflow(a)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Either or both of the given scores were too big");
        }

        if (super.isNegative(h) || super.isNegative(a)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Either or both of the given scores was negative");
        }

        ValidationResult resSkater = this.skaterGameDetailsValidator.validate(skaters);
        ValidationResult resGoalie = this.goalieGameDetailsValidator.validate(goalies);
        ValidationResult resTeam = this.teamGameDetailsValidator.validate(teams);
        ValidationResult resScoringPlay = this.scoringPlayValidator.validate(scoringPlays);

        if (!resSkater.isValid()) {
            return resSkater;
        }

        if (!resGoalie.isValid()) {
            return resGoalie;
        }

        if (!resTeam.isValid()) {
            return resTeam;
        }

        if (!resScoringPlay.isValid()) {
            return resScoringPlay;
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }
}
