package com.smhl.hdm.service.nonentities.star;

import com.smhl.hdm.comparators.StarStatsComparator;
import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.details.participant.SkaterGameDetails;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.models.nonentities.star.impl.GameStar;
import com.smhl.hdm.utils.StatisticsUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of the star service for game stars
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GameStarService {

    /**
     * We want to programmatically determine the top 3 performing players for a match
     *
     * @param game game of which we want to collect 3 stars
     * @return a list of star objects
     */
    public Object calculateStars(Game game) {

        if (Objects.isNull(game)) {
            return new ArrayList<>();
        }

        List<GameStar> stars = new ArrayList<>();
        Map<String, DoubleSummaryStatistics> gameStats = new HashMap<>();
        List<StarStats<SkaterGameDetails>> skaterStats = new ArrayList<>();

        //  collect the game stats
        CoreConstants.SKATER_GAME_DETAILS_COLLECTORS.forEach(
                (attribute, collector) -> gameStats.put(attribute, new StatisticsUtils<SkaterGameDetails>().calculateStatistics(attribute, collector, game.getGameDetails().getSkaterGameDetails()))
        );

        game.getGameDetails().getSkaterGameDetails()
                .forEach(details -> skaterStats.add(calculateStatsForGame(details, gameStats)));


        //  TODO: implement a way to always ensure the same order of stars if they have the same score (alphabetical)
        //  TODO: display these on the game page
        skaterStats.sort(StarStatsComparator.compare());
        Iterator<StarStats<SkaterGameDetails>> iterator = skaterStats.iterator();
        int count = 0;

        while (iterator.hasNext() && count < 3) {
            stars.add(obtainGameStar(iterator.next()));
            count += 1;
        }

        return stars;
    }

    /**
     * Calculates the stats for each skater attr category
     *
     * @param details skater's game we're looking at
     * @param statistics statistics for the whole game
     * @return an object holding the statistical relevance of a skater for a game
     */
    private StarStats<SkaterGameDetails> calculateStatsForGame(SkaterGameDetails details, Map<String, DoubleSummaryStatistics> statistics) {
        return new StarStats<>(
                details,
                details.getGoals() - statistics.get("goals").getAverage(),
                details.getAssists() - statistics.get("assists").getAverage(),
                details.getShots() - statistics.get("shots").getAverage(),
                details.getBlockedShots() - statistics.get("blockedShots").getAverage()
        );
    }

    /**
     * Creates a game star object from a star stat object
     *
     * @param starStats stat to be turned into a game star
     * @return game star object
     */
    private GameStar<SkaterGameDetails> obtainGameStar(StarStats<SkaterGameDetails> starStats) {
        return new GameStar<>(starStats.getDetails());
    }
}
