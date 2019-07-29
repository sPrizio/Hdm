package com.smhl.hdm.service;

import com.google.common.collect.Lists;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.models.entities.seasonstring.SeasonString;
import com.smhl.hdm.repositories.game.GameRepository;
import com.smhl.hdm.repositories.participant.goalie.GoalieRepository;
import com.smhl.hdm.repositories.participant.skater.SkaterRepository;
import com.smhl.hdm.repositories.participant.team.TeamRepository;
import com.smhl.hdm.repositories.season.GoalieSeasonRepository;
import com.smhl.hdm.repositories.season.SkaterSeasonRepository;
import com.smhl.hdm.repositories.season.TeamSeasonRepository;
import com.smhl.hdm.repositories.seasonstring.SeasonStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A special service class providing key database functionality
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class HdmDatabaseService {

    private SkaterRepository skaterRepository;
    private GoalieRepository goalieRepository;
    private TeamRepository teamRepository;

    private SkaterSeasonRepository skaterSeasonRepository;
    private GoalieSeasonRepository goalieSeasonRepository;
    private TeamSeasonRepository teamSeasonRepository;

    private GameRepository gameRepository;

    private SeasonStringRepository seasonStringRepository;

    @Autowired
    public HdmDatabaseService(
            SkaterRepository skaterRepository, GoalieRepository goalieRepository, TeamRepository teamRepository,
            SkaterSeasonRepository skaterSeasonRepository, GoalieSeasonRepository goalieSeasonRepository, TeamSeasonRepository teamSeasonRepository,
            GameRepository gameRepository,
            SeasonStringRepository seasonStringRepository
    ) {
        this.skaterRepository = skaterRepository;
        this.goalieRepository = goalieRepository;
        this.teamRepository = teamRepository;

        this.skaterSeasonRepository = skaterSeasonRepository;
        this.goalieSeasonRepository = goalieSeasonRepository;
        this.teamSeasonRepository = teamSeasonRepository;

        this.gameRepository = gameRepository;

        this.seasonStringRepository = seasonStringRepository;
    }


    //  METHODS

    public String purgeDatabase() {

        List<Game> games = Lists.newArrayList(this.gameRepository.findAll());

        games.forEach(
                game -> this.gameRepository.deleteById(game.getId())
        );

        List<SeasonString> seasonStrings = Lists.newArrayList(this.seasonStringRepository.findAll());

        seasonStrings.forEach(
                seasonString -> this.seasonStringRepository.deleteById(seasonString.getId())
        );

        List<Skater> skaters = Lists.newArrayList(this.skaterRepository.findAll());
        List<Goalie> goalies = Lists.newArrayList(this.goalieRepository.findAll());
        List<Team> teams = Lists.newArrayList(this.teamRepository.findAll());

        skaters.forEach(
                skater -> skater.getSeasons().forEach(
                        skaterSeason -> this.skaterSeasonRepository.deleteById(skaterSeason.getId())
                )
        );

        skaters.forEach(
                skater -> this.skaterRepository.deleteById(skater.getId())
        );

        goalies.forEach(
                goalie -> goalie.getSeasons().forEach(
                        goalieSeason -> this.goalieSeasonRepository.deleteById(goalieSeason.getId())
                )
        );

        goalies.forEach(
                goalie -> this.goalieRepository.deleteById(goalie.getId())
        );

        teams.forEach(
                team -> team.getSeasons().forEach(
                        teamSeason -> this.teamSeasonRepository.deleteById(teamSeason.getId())
                )
        );

        teams.forEach(
                team -> this.teamRepository.deleteById(team.getId())
        );

        return "Purge complete";
    }
}
