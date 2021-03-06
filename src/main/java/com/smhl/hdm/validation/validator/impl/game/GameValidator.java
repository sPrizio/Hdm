package com.smhl.hdm.validation.validator.impl.game;

import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.enums.GameStatus;
import com.smhl.hdm.enums.ValidationResponseResult;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.models.entities.seasonstring.SeasonString;
import com.smhl.hdm.service.entities.game.GameService;
import com.smhl.hdm.service.entities.participant.impl.TeamService;
import com.smhl.hdm.service.entities.seasonstring.SeasonStringService;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.HdmValidator;
import com.smhl.hdm.validation.validator.impl.AbstractHdmValidator;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of a validator for games. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
@SuppressWarnings("Duplicates")
public class GameValidator extends AbstractHdmValidator implements HdmValidator {

    private GameService gameService;
    private SeasonStringService seasonStringService;
    private TeamService teamService;

    private static final List<String> EXPECTED_PARAMS = Arrays.asList("gameTime", "seasonString", "gameStatus", "homeTeam", "awayTeam");

    @Autowired
    public GameValidator(GameService gameService, SeasonStringService seasonStringService, TeamService teamService) {
        this.gameService = gameService;
        this.seasonStringService = seasonStringService;
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
        String season = values.get("seasonString").toString();
        String status = values.get("gameStatus").toString();
        String home = values.get("homeTeam").toString();
        String away = values.get("awayTeam").toString();

        //  game time
        if (super.isInvalidDateForFormat(date, CoreConstants.DATE_FORMAT_LONG)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Invalid date format. Date must be of the format 'yyyy-MM-dd HH:mm:ss'");
        }

        if (this.gameService.findByGameTime(LocalDateTime.parse(date, DateTimeFormatter.ofPattern(CoreConstants.DATE_FORMAT_LONG, CoreConstants.HDM_LOCALE))) != null) {
            return new ValidationResult(ValidationResponseResult.FAILED, "A game with that game time already exists");
        }

        //  season string
        if (super.isTooLarge(season) || super.hasUnacceptableSymbol(season) || super.hasCriticalWord(season) || isInvalidSeasonString(season)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "Invalid season string. Season string is not of the form 'year1-year2' or is not a valid season string");
        }

        //  game status
        if (!EnumUtils.isValidEnum(GameStatus.class, status)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given status was not a valid option. Valid options are: " + Arrays.stream(GameStatus.values()).collect(Collectors.toList()));
        }

        //  home team & away team
        if (!super.isNumber(home) || !super.isNumber(away)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "One or both of the team values was not a valid number");
        }

        Long h = Long.parseLong(home);
        Long a = Long.parseLong(away);

        if (super.isOverflow(h) || super.isOverflow(a)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The given team id was too long");
        }

        if (h.equals(a)) {
            return new ValidationResult(ValidationResponseResult.FAILED, "The same team was supplied as both the home and away teams");
        }

        Optional<Team> hh = this.teamService.find(h);
        Optional<Team> aw = this.teamService.find(a);

        if (hh.isEmpty() || aw.isEmpty()) {
            return new ValidationResult(ValidationResponseResult.FAILED, "One or both of the given teams do not exist in the system");
        }

        return new ValidationResult(ValidationResponseResult.SUCCESSFUL, "Validation was successful");
    }


    //  HELPERS

    /**
     * Determines if the given season string exists in the system
     *
     * @param seasonString given season string to test
     * @return true if the passed season string exists in the system
     */
    private boolean isInvalidSeasonString(String seasonString) {

        return
                this.seasonStringService.getAllSeasonStrings()
                        .stream()
                        .map(SeasonString::getSeason)
                        .noneMatch(s -> s.equals(seasonString));
    }
}
