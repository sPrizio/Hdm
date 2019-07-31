package com.smhl.hdm.repositories.participant.skater;

import com.smhl.hdm.models.entities.participant.impl.Skater;
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
 * Implementation of the custom skater repository
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Repository
@SuppressWarnings({"unchecked", "StringBufferReplaceableByString"})
public class SkaterRepositoryImpl implements SkaterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    //  METHODS

    @Override
    public List<Skater> findBySeasonString(String seasonString) {

        StringBuilder queryBuilder = new StringBuilder();

        //  build query
        queryBuilder
                .append("SELECT * ")
                .append("FROM skater ")
                .append("WHERE id IN (")
                .append("   SELECT s.id ")
                .append("   FROM skater as s, skater_season as sea, skater_seasons as rel ")
                .append("   WHERE rel.skater_id = s.id ")
                .append("       AND rel.seasons_id = sea.id ")
                .append("       AND sea.season_string = ").append('\'').append(seasonString).append('\'')
                .append(");");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString(), Skater.class);

        return (List<Skater>) query.getResultList();
    }

    @Override
    public List<Skater> findBySeasonStringSorted(String seasonString, String field, String order) {

        List<Skater> skaters = new ArrayList<>();
        Map<Long, Skater> activeSkaters = new HashMap<>();

        findBySeasonString(seasonString).forEach(skater -> activeSkaters.put(skater.getId(), skater));

        StringBuilder queryBuilder = new StringBuilder();

        //  build query
        queryBuilder
                .append("SELECT s.id, (goals + assists) as points, ((goals + assists) / NULLIF(games_played, 0)) as points_per_game ")
                .append("FROM skater as s, skater_season as sea, skater_seasons as rel ")
                .append("WHERE rel.skater_id = s.id ")
                .append("   AND rel.seasons_id = sea.id ")
                .append("   AND sea.season_string = ").append('\'').append(seasonString).append('\'').append(" ")
                .append("ORDER BY ").append(field).append(" ").append(order).append(";");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString());

        query.getResultList().forEach(o -> {
            Long id = Long.parseLong(new BigInteger(((Object[]) o)[0].toString()).toString());
            skaters.add(activeSkaters.get(id));
        });

        return skaters;
    }

    @Override
    public List<Skater> findTopSkatersForStatAndLimit(String stat, Integer limit) {

        if (limit > 0) {

            List<Skater> top =
                    findBySeasonStringSorted(HdmUtils.getCurrentSeasonString(), stat, "desc")
                            .stream()
                            .filter(skater -> skater.getCurrentSeason().getGamesPlayed() > 1)
                            .collect(Collectors.toList());

            return top.isEmpty() ? new ArrayList<>() : top.subList(0, limit);
        } else {
            return new ArrayList<>();
        }
    }
}
