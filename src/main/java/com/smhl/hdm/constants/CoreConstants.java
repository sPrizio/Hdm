package com.smhl.hdm.constants;

import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.models.entities.season.impl.GoalieSeason;
import com.smhl.hdm.models.entities.season.impl.SkaterSeason;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.DoubleSummaryStatistics;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * A class that contains constants used for core functionality. We want to keep the system dynamic and logically compartmentalized, as such
 * we localize constants to this class as much as possible so changes need only be made in one spot
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class CoreConstants {

    private static final ZoneId MONTREAL = ZoneId.of("America/Montreal");

    private CoreConstants() {
        throw new UnsupportedOperationException();
    }

    public static final String NO_INSTANTIATION = "Utility/Constant classes should not be instantiated";
    public static final LocalDate NOW_AS_LOCALDATE = LocalDate.now(MONTREAL);
    public static final LocalDateTime NOW_AS_LOCALDATETIME = LocalDateTime.now(MONTREAL);
    public static final Locale HDM_LOCALE = Locale.CANADA;
    public static final String MONTREAL_GMT = "";

    public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_MEDIUM = "yyyy-MM-dd";

    //  statistical collectors
    public static final Map<String, Collector<SkaterSeason, ?, DoubleSummaryStatistics>> SKATER_SEASON_COLLECTORS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("gamesPlayed", Collectors.summarizingDouble(SkaterSeason::getGamesPlayed)),
            new AbstractMap.SimpleEntry<>("goals", Collectors.summarizingDouble(SkaterSeason::getGoals)),
            new AbstractMap.SimpleEntry<>("assists", Collectors.summarizingDouble(SkaterSeason::getAssists)),
            new AbstractMap.SimpleEntry<>("points", Collectors.summarizingDouble(SkaterSeason::getPoints)),
            new AbstractMap.SimpleEntry<>("shots", Collectors.summarizingDouble(SkaterSeason::getShots)),
            new AbstractMap.SimpleEntry<>("blockedShots", Collectors.summarizingDouble(SkaterSeason::getBlockedShots)),
            new AbstractMap.SimpleEntry<>("pointsPerGame", Collectors.summarizingDouble(SkaterSeason::getPointsPerGame))
    );

    public static final Map<String, Collector<GoalieSeason, ?, DoubleSummaryStatistics>> GOALIE_SEASON_COLLECTORS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("gamesPlayed", Collectors.summarizingDouble(GoalieSeason::getGamesPlayed)),
            new AbstractMap.SimpleEntry<>("gamesStarted", Collectors.summarizingDouble(GoalieSeason::getGamesStarted)),
            new AbstractMap.SimpleEntry<>("wins", Collectors.summarizingDouble(GoalieSeason::getWins)),
            new AbstractMap.SimpleEntry<>("losses", Collectors.summarizingDouble(GoalieSeason::getLosses)),
            new AbstractMap.SimpleEntry<>("ties", Collectors.summarizingDouble(GoalieSeason::getTies)),
            new AbstractMap.SimpleEntry<>("saves", Collectors.summarizingDouble(GoalieSeason::getSaves)),
            new AbstractMap.SimpleEntry<>("shotsAgainst", Collectors.summarizingDouble(GoalieSeason::getShotsAgainst)),
            new AbstractMap.SimpleEntry<>("goalsAgainst", Collectors.summarizingDouble(GoalieSeason::getGoalsAgainst)),
            new AbstractMap.SimpleEntry<>("shutouts", Collectors.summarizingDouble(GoalieSeason::getShutouts)),
            new AbstractMap.SimpleEntry<>("savePercentage", Collectors.summarizingDouble(GoalieSeason::getSavePercentage)),
            new AbstractMap.SimpleEntry<>("goalsAgainstAverage", Collectors.summarizingDouble(GoalieSeason::getGoalsAgainstAverage))
    );

    public static final Map<String, Collector<SkaterGameDetails, ?, DoubleSummaryStatistics>> SKATER_GAME_DETAILS_COLLECTORS = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("goals", Collectors.summarizingDouble(SkaterGameDetails::getGoals)),
            new AbstractMap.SimpleEntry<>("assists", Collectors.summarizingDouble(SkaterGameDetails::getAssists)),
            new AbstractMap.SimpleEntry<>("shots", Collectors.summarizingDouble(SkaterGameDetails::getShots)),
            new AbstractMap.SimpleEntry<>("blockedShots", Collectors.summarizingDouble(SkaterGameDetails::getBlockedShots))
    );

    //  stars
    public static final Double GOAL_RANK_MULTIPLIER = 3.3;
    public static final Double ASSIST_RANK_MULTIPLIER = 2.15;
    public static final Double SHOT_RANK_MULTIPLIER = 1.1;
    public static final Double BLOCKED_SHOT_RANK_MULTIPLIER = 1.35;
}
