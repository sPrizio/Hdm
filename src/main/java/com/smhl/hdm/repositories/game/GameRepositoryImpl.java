package com.smhl.hdm.repositories.game;

import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Implementation of the custom DAO for games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Repository
@SuppressWarnings({"unchecked", "StringBufferReplaceableByString"})
public class GameRepositoryImpl implements GameRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SkaterGameDetails> findSkaterGameDetails(String seasonString, Long id, int limit) {

        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder
                .append("SELECT sgd.id, sgd.game_time, sgd.assists, sgd.blocked_shots, sgd.goals, sgd.shots, sgd.skater_id, sgd.team_id, sgd.game_details_id ")
                .append("FROM game as g, game_details as gd, skater_game_details as sgd ")
                .append("WHERE g.game_details_id = gd.id ")
                .append("   AND g.season_string = ").append('\'').append(seasonString).append('\'')
                .append("   AND gd.id = sgd.game_details_id ")
                .append("   AND sgd.skater_id = ").append(id).append(" ")
                .append("ORDER BY g.game_time DESC LIMIT ").append(limit).append(";");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString(), SkaterGameDetails.class);

        return (List<SkaterGameDetails>) query.getResultList();
    }

    @Override
    public List<GoalieGameDetails> findGoalieGameDetails(String seasonString, Long id, int limit) {

        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder
                .append("SELECT ggd.id, ggd.game_time, ggd.game_result, ggd.goals_against, ggd.is_starter, ggd.saves, ggd.shots_against, ggd.goalie_id, ggd.team_id, ggd.game_details_id ")
                .append("FROM game as g, game_details as gd, goalie_game_details as ggd ")
                .append("WHERE g.game_details_id = gd.id ")
                .append("   AND g.season_string = ").append('\'').append(seasonString).append('\'')
                .append("   AND gd.id = ggd.game_details_id ")
                .append("   AND ggd.goalie_id = ").append(id).append(" ")
                .append("ORDER BY g.game_time DESC LIMIT ").append(limit).append(";");

        Query query = this.entityManager.createNativeQuery(queryBuilder.toString(), GoalieGameDetails.class);

        return (List<GoalieGameDetails>) query.getResultList();
    }
}
