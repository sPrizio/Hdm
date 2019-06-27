package com.smhl.hdm.service.nonentities.star;

import com.smhl.hdm.comparators.StarStatsComparator;
import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.models.nonentities.star.StarStats;
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
    public List<GameStar> calculateStars(Game game) {

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

        skaterStats.sort(StarStatsComparator.compare());

        if (skaterStats.size() > 2) {
            for (int i = skaterStats.size() - 1; i > ((skaterStats.size() - 1) - 3); i = i - 1) {
                stars.add(obtainGameStar(skaterStats.get(i)));
            }

            //  sort equivalent stars by name
            if (
                    stars.get(0).getScore().equals(stars.get(1).getScore()) &&
                    stars.get(0).getDetails().getParticipant().getName().compareTo(stars.get(1).getDetails().getParticipant().getName()) > 0
            ) {
                Collections.swap(stars, 0, 1);
            }

            if (
                    stars.get(1).getScore().equals(stars.get(2).getScore()) &&
                    stars.get(1).getDetails().getParticipant().getName().compareTo(stars.get(2).getDetails().getParticipant().getName()) > 0
            ) {
                Collections.swap(stars, 1, 2);
            }
        }

        return stars;
    }


    //  HELPERS

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
                statistics
        );
    }

    /**
     * Creates a game star object from a star stat object
     *
     * @param starStats stat to be turned into a game star
     * @return game star object
     */
    private GameStar<SkaterGameDetails> obtainGameStar(StarStats<SkaterGameDetails> starStats) {
        return new GameStar<>(starStats.getDetails(), starStats.getScore());
    }
}
