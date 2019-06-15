package com.smhl.hdm.commandlinerunners;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.smhl.hdm.constants.CoreConstants;
import com.smhl.hdm.enums.GameResult;
import com.smhl.hdm.enums.GameStatus;
import com.smhl.hdm.models.entities.details.game.GameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.GoalieGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.SkaterGameDetails;
import com.smhl.hdm.models.entities.details.participant.impl.TeamGameDetails;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.models.entities.game.ScoringPlay;
import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.repositories.details.GameDetailsRepository;
import com.smhl.hdm.repositories.game.GameRepository;
import com.smhl.hdm.repositories.participant.goalie.GoalieRepository;
import com.smhl.hdm.repositories.participant.skater.SkaterRepository;
import com.smhl.hdm.repositories.participant.team.TeamRepository;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Loads Game data into the database for testing
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
@Order(3)
public class GameDataLoader implements CommandLineRunner {

    private LocalDateTime dateTime = LocalDateTime.of(CoreConstants.NOW_AS_LOCALDATE.getYear(), 1, 1, 13, 0, 0);

    private SkaterRepository skaterRepository;
    private GoalieRepository goalieRepository;
    private TeamRepository teamRepository;
    private GameRepository gameRepository;
    private GameDetailsRepository gameDetailsRepository;

    @Autowired
    public GameDataLoader(
            SkaterRepository skaterRepository,
            GoalieRepository goalieRepository,
            TeamRepository teamRepository,
            GameRepository gameRepository,
            GameDetailsRepository gameDetailsRepository
    ) {
        this.skaterRepository = skaterRepository;
        this.goalieRepository = goalieRepository;
        this.teamRepository = teamRepository;
        this.gameRepository = gameRepository;
        this.gameDetailsRepository = gameDetailsRepository;
    }

    @Override
    public void run(String... args) {
        loadGameData();
    }

    /**
     * Loads Game data
     */
    private void loadGameData() {

        List<Skater> skaters = Lists.newArrayList(this.skaterRepository.findAll());
        List<Goalie> goalies = Lists.newArrayList(this.goalieRepository.findAll());
        List<Team> teams = Lists.newArrayList(this.teamRepository.findAll());

        Team team1 = teams.get(0);
        Team team2 = teams.get(1);
        Team team3 = teams.get(2);
        Team team4 = teams.get(3);
        Team team5 = teams.get(4);

        Game game1 = new Game(dateTime, HdmUtils.getCurrentSeasonString(), GameStatus.COMPLETE.toString(), team1, team2);
        Game game2 = new Game(dateTime.plusWeeks(1), HdmUtils.getCurrentSeasonString(), GameStatus.COMPLETE.toString(), team3, team1);
        Game game3 = new Game(dateTime.plusWeeks(2), HdmUtils.getCurrentSeasonString(), GameStatus.COMPLETE.toString(), team4, team3);
        Game game4 = new Game(dateTime.plusWeeks(3), HdmUtils.getCurrentSeasonString(), GameStatus.COMPLETE.toString(), team2, team5);
        Game game5 = new Game(dateTime.plusWeeks(4), HdmUtils.getCurrentSeasonString(), GameStatus.COMPLETE.toString(), team5, team4);

        //  GAME 1

        //  scoring plays
        ScoringPlay scoringPlay11 = new ScoringPlay(team1, skaters.get(0), skaters.get(1), null);
        ScoringPlay scoringPlay12 = new ScoringPlay(team1, skaters.get(2), null, null);
        ScoringPlay scoringPlay13 = new ScoringPlay(team2, skaters.get(9), skaters.get(8), skaters.get(7));
        ScoringPlay scoringPlay14 = new ScoringPlay(team1, skaters.get(2), skaters.get(3), skaters.get(4));
        ScoringPlay scoringPlay15 = new ScoringPlay(team2, skaters.get(5), null, null);

        //  skater details
        SkaterGameDetails skaterGameDetails11 = new SkaterGameDetails(dateTime, skaters.get(0), team1, 1, 0, 9, 0);
        SkaterGameDetails skaterGameDetails12 = new SkaterGameDetails(dateTime, skaters.get(1), team1, 0, 1, 6, 1);
        SkaterGameDetails skaterGameDetails13 = new SkaterGameDetails(dateTime, skaters.get(2), team1, 2, 0, 8, 2);
        SkaterGameDetails skaterGameDetails14 = new SkaterGameDetails(dateTime, skaters.get(3), team1, 0, 1, 10, 0);
        SkaterGameDetails skaterGameDetails15 = new SkaterGameDetails(dateTime, skaters.get(4), team1, 0, 1, 4, 3);
        SkaterGameDetails skaterGameDetails16 = new SkaterGameDetails(dateTime, skaters.get(5), team2, 1, 0, 1, 1);
        SkaterGameDetails skaterGameDetails17 = new SkaterGameDetails(dateTime, skaters.get(6), team2, 0, 0, 8, 4);
        SkaterGameDetails skaterGameDetails18 = new SkaterGameDetails(dateTime, skaters.get(7), team2, 0, 1, 11, 1);
        SkaterGameDetails skaterGameDetails19 = new SkaterGameDetails(dateTime, skaters.get(8), team2, 0, 1, 13, 0);
        SkaterGameDetails skaterGameDetails110 = new SkaterGameDetails(dateTime, skaters.get(9), team2, 1, 0, 7, 6);

        //  goalie details
        GoalieGameDetails goalieGameDetails11 = new GoalieGameDetails(dateTime, goalies.get(0), team1, true, GameResult.WIN.toString(), 40, 38, 2);
        GoalieGameDetails goalieGameDetails12 = new GoalieGameDetails(dateTime, goalies.get(1), team2, true, GameResult.LOSS.toString(), 37, 34, 3);

        //  team details
        TeamGameDetails teamGameDetails11 = new TeamGameDetails(dateTime, team1, GameResult.WIN.toString(), 3, 2);
        TeamGameDetails teamGameDetails12 = new TeamGameDetails(dateTime, team2, GameResult.LOSS.toString(), 2, 3);

        GameDetails gameDetails1 = new GameDetails(
                dateTime,
                3,
                2,
                Sets.newHashSet(
                        skaterGameDetails11, skaterGameDetails12, skaterGameDetails13, skaterGameDetails14, skaterGameDetails15,
                        skaterGameDetails16, skaterGameDetails17, skaterGameDetails18, skaterGameDetails19, skaterGameDetails110
                ),
                Sets.newHashSet(goalieGameDetails11, goalieGameDetails12),
                Sets.newHashSet(teamGameDetails11, teamGameDetails12),
                Sets.newHashSet(scoringPlay11, scoringPlay12, scoringPlay13, scoringPlay14, scoringPlay15)
        );

        //  GAME 2

        //  scoring plays
        ScoringPlay scoringPlay21 = new ScoringPlay(team3, skaters.get(4), skaters.get(3), skaters.get(0));
        ScoringPlay scoringPlay22 = new ScoringPlay(team3, skaters.get(0), skaters.get(2), null);
        ScoringPlay scoringPlay23 = new ScoringPlay(team1, skaters.get(7), skaters.get(8), skaters.get(5));
        ScoringPlay scoringPlay24 = new ScoringPlay(team3, skaters.get(0), null, null);
        ScoringPlay scoringPlay25 = new ScoringPlay(team3, skaters.get(1), skaters.get(3), skaters.get(4));

        //  skater details
        SkaterGameDetails skaterGameDetails21 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(0), team3, 2, 1, 8, 0);
        SkaterGameDetails skaterGameDetails22 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(1), team3, 1, 0, 15, 0);
        SkaterGameDetails skaterGameDetails23 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(2), team3, 0, 1, 21, 1);
        SkaterGameDetails skaterGameDetails24 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(3), team3, 2, 2, 7, 3);
        SkaterGameDetails skaterGameDetails25 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(4), team3, 1, 1, 6, 4);
        SkaterGameDetails skaterGameDetails26 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(5), team1, 0, 1, 13, 1);
        SkaterGameDetails skaterGameDetails28 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(7), team1, 1, 0, 23, 6);
        SkaterGameDetails skaterGameDetails29 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(8), team1, 0, 1, 5, 1);
        SkaterGameDetails skaterGameDetails210 = new SkaterGameDetails(dateTime.plusWeeks(1), skaters.get(9), team1, 0, 0, 8, 2);

        //  goalie details
        GoalieGameDetails goalieGameDetails21 = new GoalieGameDetails(dateTime.plusWeeks(1), goalies.get(2), team3, true, GameResult.WIN.toString(), 49, 48, 1);
        GoalieGameDetails goalieGameDetails22 = new GoalieGameDetails(dateTime.plusWeeks(1), goalies.get(3), team1, true, GameResult.LOSS.toString(), 57, 53, 4);

        //  team details
        TeamGameDetails teamGameDetails21 = new TeamGameDetails(dateTime.plusWeeks(1), team3, GameResult.WIN.toString(), 4, 1);
        TeamGameDetails teamGameDetails22 = new TeamGameDetails(dateTime.plusWeeks(1), team1, GameResult.LOSS.toString(), 1, 4);

        GameDetails gameDetails2 = new GameDetails(
                dateTime.plusWeeks(1),
                4,
                1,
                Sets.newHashSet(
                        skaterGameDetails21, skaterGameDetails22, skaterGameDetails23, skaterGameDetails24, skaterGameDetails25,
                        skaterGameDetails26, skaterGameDetails28, skaterGameDetails29, skaterGameDetails210
                ),
                Sets.newHashSet(goalieGameDetails21, goalieGameDetails22),
                Sets.newHashSet(teamGameDetails21, teamGameDetails22),
                Sets.newHashSet(scoringPlay21, scoringPlay22, scoringPlay23, scoringPlay24, scoringPlay25)
        );

        //  GAME 3

        //  scoring plays
        ScoringPlay scoringPlay31 = new ScoringPlay(team4, skaters.get(6), skaters.get(9), skaters.get(5));
        ScoringPlay scoringPlay32 = new ScoringPlay(team4, skaters.get(6), skaters.get(7), skaters.get(5));

        //  skater details
        SkaterGameDetails skaterGameDetails31 = new SkaterGameDetails(dateTime.plusWeeks(2), skaters.get(0), team4, 0, 0, 10, 1);
        SkaterGameDetails skaterGameDetails34 = new SkaterGameDetails(dateTime.plusWeeks(2), skaters.get(3), team4, 0, 0, 18, 4);
        SkaterGameDetails skaterGameDetails35 = new SkaterGameDetails(dateTime.plusWeeks(2), skaters.get(4), team4, 0, 0, 9, 5);
        SkaterGameDetails skaterGameDetails36 = new SkaterGameDetails(dateTime.plusWeeks(2), skaters.get(5), team3, 0, 2, 10, 1);
        SkaterGameDetails skaterGameDetails37 = new SkaterGameDetails(dateTime.plusWeeks(2), skaters.get(6), team3, 2, 0, 3, 2);
        SkaterGameDetails skaterGameDetails38 = new SkaterGameDetails(dateTime.plusWeeks(2), skaters.get(7), team3, 0, 1, 4, 4);
        SkaterGameDetails skaterGameDetails310 = new SkaterGameDetails(dateTime.plusWeeks(2), skaters.get(9), team3, 0, 1, 11, 6);

        //  goalie details
        GoalieGameDetails goalieGameDetails31 = new GoalieGameDetails(dateTime.plusWeeks(2), goalies.get(4), team4, true, GameResult.WIN.toString(), 28, 28, 0);
        GoalieGameDetails goalieGameDetails32 = new GoalieGameDetails(dateTime.plusWeeks(2), goalies.get(0), team3, true, GameResult.LOSS.toString(), 47, 45, 2);

        //  team details
        TeamGameDetails teamGameDetails31 = new TeamGameDetails(dateTime.plusWeeks(2), team4, GameResult.WIN.toString(), 2, 0);
        TeamGameDetails teamGameDetails32 = new TeamGameDetails(dateTime.plusWeeks(2), team3, GameResult.LOSS.toString(), 0, 2);

        GameDetails gameDetails3 = new GameDetails(
                dateTime.plusWeeks(2),
                2,
                0,
                Sets.newHashSet(
                        skaterGameDetails31, skaterGameDetails34, skaterGameDetails35,
                        skaterGameDetails36, skaterGameDetails37, skaterGameDetails38, skaterGameDetails310
                ),
                Sets.newHashSet(goalieGameDetails31, goalieGameDetails32),
                Sets.newHashSet(teamGameDetails31, teamGameDetails32),
                Sets.newHashSet(scoringPlay31, scoringPlay32)
        );

        //  GAME 4

        //  scoring plays
        ScoringPlay scoringPlay41 = new ScoringPlay(team2, skaters.get(0), skaters.get(3), skaters.get(2));
        ScoringPlay scoringPlay42 = new ScoringPlay(team5, skaters.get(5), skaters.get(6), null);
        ScoringPlay scoringPlay43 = new ScoringPlay(team5, skaters.get(6), skaters.get(5), null);
        ScoringPlay scoringPlay44 = new ScoringPlay(team2, skaters.get(1), skaters.get(4), null);
        ScoringPlay scoringPlay45 = new ScoringPlay(team5, skaters.get(8), skaters.get(7), skaters.get(6));
        ScoringPlay scoringPlay46 = new ScoringPlay(team2, skaters.get(1), skaters.get(3), skaters.get(4));

        //  skater details
        SkaterGameDetails skaterGameDetails41 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(0), team2, 1, 0, 10, 2);
        SkaterGameDetails skaterGameDetails42 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(1), team2, 2, 0, 14, 2);
        SkaterGameDetails skaterGameDetails43 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(2), team2, 0, 1, 15, 1);
        SkaterGameDetails skaterGameDetails44 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(3), team2, 0, 2, 9, 0);
        SkaterGameDetails skaterGameDetails45 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(4), team2, 0, 2, 6, 0);
        SkaterGameDetails skaterGameDetails46 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(5), team5, 1, 1, 11, 3);
        SkaterGameDetails skaterGameDetails47 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(6), team5, 1, 1, 12, 4);
        SkaterGameDetails skaterGameDetails48 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(7), team5, 0, 1, 4, 0);
        SkaterGameDetails skaterGameDetails49 = new SkaterGameDetails(dateTime.plusWeeks(3), skaters.get(8), team5, 1, 0, 10, 1);

        //  goalie details
        GoalieGameDetails goalieGameDetails41 = new GoalieGameDetails(dateTime.plusWeeks(3), goalies.get(1), team2, true, GameResult.TIE.toString(), 27, 24, 3);
        GoalieGameDetails goalieGameDetails42 = new GoalieGameDetails(dateTime.plusWeeks(3), goalies.get(2), team5, true, GameResult.TIE.toString(), 54, 51, 3);

        //  team details
        TeamGameDetails teamGameDetails41 = new TeamGameDetails(dateTime.plusWeeks(3), team2, GameResult.WIN.toString(), 3, 3);
        TeamGameDetails teamGameDetails42 = new TeamGameDetails(dateTime.plusWeeks(3), team5, GameResult.LOSS.toString(), 3, 3);

        GameDetails gameDetails4 = new GameDetails(
                dateTime.plusWeeks(3),
                3,
                3,
                Sets.newHashSet(
                        skaterGameDetails41, skaterGameDetails42, skaterGameDetails43, skaterGameDetails44, skaterGameDetails45,
                        skaterGameDetails46, skaterGameDetails47, skaterGameDetails48, skaterGameDetails49
                ),
                Sets.newHashSet(goalieGameDetails41, goalieGameDetails42),
                Sets.newHashSet(teamGameDetails41, teamGameDetails42),
                Sets.newHashSet(scoringPlay41, scoringPlay42, scoringPlay43, scoringPlay44, scoringPlay45, scoringPlay46)
        );

        //  GAME 5

        //  scoring plays
        ScoringPlay scoringPlay51 = new ScoringPlay(team4, skaters.get(0), skaters.get(3), null);
        ScoringPlay scoringPlay52 = new ScoringPlay(team5, skaters.get(9), null, null);
        ScoringPlay scoringPlay53 = new ScoringPlay(team4, skaters.get(0), null, null);
        ScoringPlay scoringPlay54 = new ScoringPlay(team4, skaters.get(0), skaters.get(1), skaters.get(4));

        //  skater details
        SkaterGameDetails skaterGameDetails51 = new SkaterGameDetails(dateTime.plusWeeks(4), skaters.get(0), team5, 3, 0, 10, 1);
        SkaterGameDetails skaterGameDetails52 = new SkaterGameDetails(dateTime.plusWeeks(4), skaters.get(1), team5, 0, 1, 11, 6);
        SkaterGameDetails skaterGameDetails54 = new SkaterGameDetails(dateTime.plusWeeks(4), skaters.get(3), team5, 0, 1, 4, 4);
        SkaterGameDetails skaterGameDetails55 = new SkaterGameDetails(dateTime.plusWeeks(4), skaters.get(4), team5, 0, 1, 5, 3);
        SkaterGameDetails skaterGameDetails56 = new SkaterGameDetails(dateTime.plusWeeks(4), skaters.get(5), team4, 0, 0, 11, 1);
        SkaterGameDetails skaterGameDetails59 = new SkaterGameDetails(dateTime.plusWeeks(4), skaters.get(8), team4, 0, 0, 18, 1);
        SkaterGameDetails skaterGameDetails510 = new SkaterGameDetails(dateTime.plusWeeks(4), skaters.get(9), team4, 1, 0, 13, 0);

        //  goalie details
        GoalieGameDetails goalieGameDetails51 = new GoalieGameDetails(dateTime.plusWeeks(4), goalies.get(3), team5, true, GameResult.LOSS.toString(), 42, 39, 3);
        GoalieGameDetails goalieGameDetails52 = new GoalieGameDetails(dateTime.plusWeeks(4), goalies.get(4), team4, true, GameResult.WIN.toString(), 30, 29, 1);

        //  team details
        TeamGameDetails teamGameDetails51 = new TeamGameDetails(dateTime.plusWeeks(4), team5, GameResult.WIN.toString(), 1, 3);
        TeamGameDetails teamGameDetails52 = new TeamGameDetails(dateTime.plusWeeks(4), team4, GameResult.LOSS.toString(), 3, 1);

        GameDetails gameDetails5 = new GameDetails(
                dateTime.plusWeeks(4),
                1,
                3,
                Sets.newHashSet(
                        skaterGameDetails51, skaterGameDetails52, skaterGameDetails54, skaterGameDetails55,
                        skaterGameDetails56, skaterGameDetails59, skaterGameDetails510
                ),
                Sets.newHashSet(goalieGameDetails51, goalieGameDetails52),
                Sets.newHashSet(teamGameDetails51, teamGameDetails52),
                Sets.newHashSet(scoringPlay51, scoringPlay52, scoringPlay53, scoringPlay54)
        );

        gameDetails1 = this.gameDetailsRepository.save(gameDetails1);
        gameDetails2 = this.gameDetailsRepository.save(gameDetails2);
        gameDetails3 = this.gameDetailsRepository.save(gameDetails3);
        gameDetails4 = this.gameDetailsRepository.save(gameDetails4);
        gameDetails5 = this.gameDetailsRepository.save(gameDetails5);

        game1.setGameDetails(gameDetails1);
        game2.setGameDetails(gameDetails2);
        game3.setGameDetails(gameDetails3);
        game4.setGameDetails(gameDetails4);
        game5.setGameDetails(gameDetails5);

        this.gameRepository.save(game1);
        this.gameRepository.save(game2);
        this.gameRepository.save(game3);
        this.gameRepository.save(game4);
        this.gameRepository.save(game5);
    }
}
