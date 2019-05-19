package com.smhl.hdm.repositories.participant;

import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.repositories.HdmRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DAO access-layer for Skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface SkaterRepository extends HdmRepository, CrudRepository<Skater, Long> {

    List<Skater> getAllByActive(boolean isActive);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM skater as s, skater_season as sea, skater_seasons as rel " +
                    "WHERE rel.skater_id = s.id " +
                    "   AND rel.seasons_id = sea.id " +
                    "   AND sea.season_string = :seasonString " +
                    "   AND s.active = :isActive " +
                    "ORDER BY :field;")
    List<Skater> getAllByActiveSortedAsc(
            @Param("active") boolean isActive,
            @Param("seasonString") String currentSeasonString,
            @Param("field") String field
    );

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM skater as s, skater_season as sea, skater_seasons as rel " +
                    "WHERE rel.skater_id = s.id " +
                    "   AND rel.seasons_id = sea.id " +
                    "   AND sea.season_string = :seasonString " +
                    "   AND s.active = :isActive " +
                    "ORDER BY :field DESC;")
    List<Skater> getAllByActiveSortedDesc(
            @Param("active") boolean isActive,
            @Param("seasonString") String currentSeasonString,
            @Param("field") String field
    );
}
