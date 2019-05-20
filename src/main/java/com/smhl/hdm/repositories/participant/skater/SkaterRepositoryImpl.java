package com.smhl.hdm.repositories.participant.skater;

import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.repositories.participant.SkaterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the custom skater repository
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@SuppressWarnings({"unchecked", "SpringJavaAutowiredMembersInspection"})
public class SkaterRepositoryImpl implements SkaterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SkaterRepository skaterRepository;

    @Override
    public List<Skater> findByActiveSorted(boolean isActive, String seasonString, String field, String order) {

        List<Skater> skaters = new ArrayList<>();
        Map<Long, Skater> activeSkaters = new HashMap<>();

        this.skaterRepository.getAllByActive(true).forEach(skater -> activeSkaters.put(skater.getId(), skater));

        StringBuilder queryBuilder = new StringBuilder();

        //  build query
        queryBuilder
                .append("SELECT s.id, (goals + assists) as points, ((goals + assists) / NULLIF(games_played, 0)) as points_per_game ")
                .append("FROM skater as s, skater_season as sea, skater_seasons as rel ")
                .append("WHERE rel.skater_id = s.id ")
                .append("   AND rel.seasons_id = sea.id ")
                .append("   AND sea.season_string = ").append('\'').append(seasonString).append('\'').append(" ")
                .append("   AND s.active = ").append(isActive).append(" ")
                .append("ORDER BY ").append(field).append(" ").append(order).append(";");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString());

        query.getResultList().forEach(o -> {
            Long id = Long.parseLong(new BigInteger(((Object[]) o)[0].toString()).toString());
            skaters.add(activeSkaters.get(id));
        });

        return skaters;
    }
}
