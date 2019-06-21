package com.smhl.hdm.repositories.participant.goalie;

import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the custom dao for goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Repository
@SuppressWarnings({"unchecked", "StringBufferReplaceableByString"})
public class GoalieRepositoryImpl implements GoalieRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Goalie> findBySeasonString(String seasonString) {

        StringBuilder queryBuilder = new StringBuilder();

        //  build query
        queryBuilder
                .append("SELECT * ")
                .append("FROM goalie ")
                .append("WHERE id in (")
                .append("   SELECT g.id ")
                .append("   FROM goalie as g, goalie_season as sea, goalie_seasons as rel ")
                .append("   WHERE rel.goalie_id = g.id ")
                .append("       AND rel.seasons_id = sea.id")
                .append("       AND sea.season_string = ").append('\'').append(seasonString).append('\'')
                .append(");");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString(), Goalie.class);

        return (List<Goalie>) query.getResultList();
    }

    @Override
    public List<Goalie> findBySeasonStringSorted(String seasonString, String field, String order) {

        List<Goalie> goalies = new ArrayList<>();
        Map<Long, Goalie> activeGoalies = new HashMap<>();

        findBySeasonString(seasonString).forEach(goalie -> activeGoalies.put(goalie.getId(), goalie));

        StringBuilder queryBuilder = new StringBuilder();

        //  build query
        queryBuilder
                .append("SELECT g.id, (saves / NULLIF(shots_against, 0)) as save_percentage, (goals_against / NULLIF(games_played, 0)) as goals_against_average ")
                .append("FROM goalie as g, goalie_season as sea, goalie_seasons as rel ")
                .append("WHERE rel.goalie_id = g.id ")
                .append("   AND rel.seasons_id = sea.id")
                .append("   AND sea.season_string = ").append('\'').append(seasonString).append('\'').append(" ")
                .append("ORDER BY ").append(field).append(" ").append(order).append(";");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString());

        query.getResultList().forEach(o -> {
            Long id = Long.parseLong(new BigInteger(((Object[]) o)[0].toString()).toString());
            goalies.add(activeGoalies.get(id));
        });

        return goalies;
    }

    @Override
    public List<Goalie> findTopGoaliesForStatAndLimit(String stat, Integer limit) {

        if (limit > 0) {
            String order = stat.equals("goals_against_average") ? "asc" : "desc";
            return
                    findBySeasonStringSorted(HdmUtils.getCurrentSeasonString(), stat, order)
                            .stream()
                            .filter(goalie -> goalie.getCurrentSeason().getGamesPlayed() > 1)
                            .collect(Collectors.toList())
                            .subList(0, limit);
        } else {
            return new ArrayList<>();
        }
    }
}
