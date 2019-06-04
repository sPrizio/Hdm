package com.smhl.hdm.repositories.participant.team;

import com.smhl.hdm.models.entities.participant.impl.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the custom team repository
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Repository
@SuppressWarnings({"unchecked", "StringBufferReplaceableByString"})
public class TeamRepositoryImpl implements TeamRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Team> findBySeasonString(String seasonString) {

        StringBuilder queryBuilder = new StringBuilder();

        //  build query
        queryBuilder
                .append("SELECT * ")
                .append("FROM team ")
                .append("WHERE id IN (")
                .append("   SELECT t.id ")
                .append("   FROM team as t, team_season as sea, team_seasons as rel ")
                .append("   WHERE rel.team_id = t.id ")
                .append("       AND rel.seasons_id = sea.id ")
                .append("       AND sea.season_string = ").append('\'').append(seasonString).append('\'')
                .append(");");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString(), Team.class);

        return (List<Team>) query.getResultList();
    }

    @Override
    public List<Team> findBySeasonStringSorted(String seasonString, String field, String order) {

        List<Team> teams = new ArrayList<>();
        Map<Long, Team> activeTeams = new HashMap<>();

        findBySeasonString(seasonString).forEach(team -> activeTeams.put(team.getId(), team));

        StringBuilder queryBuilder = new StringBuilder();

        //  build query
        queryBuilder
                .append("SELECT t.id, ((wins * 2) + ties) as points, (goals_for - goals_against) as differential ")
                .append("FROM team as t, team_season as sea, team_seasons as rel ")
                .append("WHERE rel.team_id = t.id ")
                .append("   AND rel.seasons_id = sea.id ")
                .append("   AND sea.season_string = ").append('\'').append(seasonString).append('\'').append(" ")
                .append("ORDER BY ").append(field).append(" ").append(order).append(";");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString());

        query.getResultList().forEach(o -> {
            Long id = Long.parseLong(new BigInteger(((Object[]) o)[0].toString()).toString());
            teams.add(activeTeams.get(id));
        });

        return teams;
    }
}
