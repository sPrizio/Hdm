package com.smhl.hdm.commandlinerunners;

import com.google.common.collect.Sets;
import com.smhl.hdm.enums.ParticipantPosition;
import com.smhl.hdm.models.participant.impl.Goalie;
import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.models.participant.impl.Team;
import com.smhl.hdm.models.season.impl.GoalieSeason;
import com.smhl.hdm.models.season.impl.SkaterSeason;
import com.smhl.hdm.models.season.impl.TeamSeason;
import com.smhl.hdm.repositories.participant.GoalieRepository;
import com.smhl.hdm.repositories.participant.SkaterRepository;
import com.smhl.hdm.repositories.participant.TeamRepository;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Loads test data for Participants
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
@Order(1)
public class ParticipantDataLoader implements CommandLineRunner {

    private static final String SEASON1 = HdmUtils.getCurrentSeasonString(-2);
    private static final String SEASON2 = HdmUtils.getCurrentSeasonString(-1);
    private static final String SEASON3 = HdmUtils.getCurrentSeasonString();

    private SkaterRepository skaterRepository;
    private GoalieRepository goalieRepository;
    private TeamRepository teamRepository;

    @Autowired
    public ParticipantDataLoader(SkaterRepository skaterRepository, GoalieRepository goalieRepository, TeamRepository teamRepository) {
        this.skaterRepository = skaterRepository;
        this.goalieRepository = goalieRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) {
        loadSkaterData();
        loadGoalieData();
        loadTeamData();
    }

    /**
     * Loads all skater data into the database for testing
     */
    private void loadSkaterData() {
        Skater skater1 = new Skater("Greg", "Wilson", ParticipantPosition.CENTER.toString());
        Skater skater2 = new Skater("Scott", "Johnson", ParticipantPosition.LEFT_WING.toString());
        Skater skater3 = new Skater("Jesse", "Smith", ParticipantPosition.CENTER.toString());
        Skater skater4 = new Skater("Kyle", "Brown", ParticipantPosition.RIGHT_WING.toString());
        Skater skater5 = new Skater("Zachary", "Jackson", ParticipantPosition.CENTER.toString());
        Skater skater6 = new Skater("Tyler", "Martin", ParticipantPosition.DEFENSE.toString());
        Skater skater7 = new Skater("Brad", "Morris", ParticipantPosition.CENTER.toString());
        Skater skater8 = new Skater("Pete", "Austin", ParticipantPosition.DEFENSE.toString());
        Skater skater9 = new Skater("Derek", "Payne", ParticipantPosition.CENTER.toString());
        Skater skater10 = new Skater("Alex", "Hall", ParticipantPosition.LEFT_WING.toString());

        SkaterSeason skaterSeason11 = new SkaterSeason(SEASON1, 82, 20, 52, 223, 24, skater1);
        SkaterSeason skaterSeason12 = new SkaterSeason(SEASON2, 78, 30, 36, 250, 44, skater1);
        SkaterSeason skaterSeason13 = new SkaterSeason(SEASON3, 82, 45, 53, 234, 81, skater1);

        SkaterSeason skaterSeason21 = new SkaterSeason(SEASON1, 79, 35, 65, 220, 13, skater2);
        SkaterSeason skaterSeason22 = new SkaterSeason(SEASON2, 77, 28, 54, 303, 42, skater2);
        SkaterSeason skaterSeason23 = new SkaterSeason(SEASON3, 78, 34, 48, 211, 61, skater2);

        SkaterSeason skaterSeason31 = new SkaterSeason(SEASON1, 78, 20, 37, 145, 22, skater3);
        SkaterSeason skaterSeason32 = new SkaterSeason(SEASON2, 81, 27, 51, 182, 158, skater3);
        SkaterSeason skaterSeason33 = new SkaterSeason(SEASON3, 81, 22, 38, 156, 128, skater3);

        SkaterSeason skaterSeason41 = new SkaterSeason(SEASON1, 74, 28, 33, 229, 133, skater4);
        SkaterSeason skaterSeason42 = new SkaterSeason(SEASON2, 81, 20, 53, 139, 38, skater4);
        SkaterSeason skaterSeason43 = new SkaterSeason(SEASON3, 82, 41, 87, 246, 121, skater4);

        SkaterSeason skaterSeason51 = new SkaterSeason(SEASON1, 81, 51, 38, 338, 147, skater5);
        SkaterSeason skaterSeason52 = new SkaterSeason(SEASON2, 82, 50, 55, 231, 67, skater5);
        SkaterSeason skaterSeason53 = new SkaterSeason(SEASON3, 82, 36, 63, 245, 44, skater5);

        SkaterSeason skaterSeason61 = new SkaterSeason(SEASON1, 82, 24, 32, 169, 49, skater6);
        SkaterSeason skaterSeason62 = new SkaterSeason(SEASON2, 82, 35, 61, 206, 41, skater6);
        SkaterSeason skaterSeason63 = new SkaterSeason(SEASON3, 80, 13, 57, 185, 36, skater6);

        SkaterSeason skaterSeason71 = new SkaterSeason(SEASON1, 80, 14, 50, 136, 40, skater7);
        SkaterSeason skaterSeason72 = new SkaterSeason(SEASON2, 81, 44, 66, 341, 93, skater7);
        SkaterSeason skaterSeason73 = new SkaterSeason(SEASON3, 82, 25, 34, 278, 40, skater7);

        SkaterSeason skaterSeason81 = new SkaterSeason(SEASON1, 82, 40, 36, 227, 40, skater8);
        SkaterSeason skaterSeason82 = new SkaterSeason(SEASON2, 79, 28, 59, 191, 43, skater8);
        SkaterSeason skaterSeason83 = new SkaterSeason(SEASON3, 70, 12, 42, 182, 38, skater8);

        SkaterSeason skaterSeason91 = new SkaterSeason(SEASON1, 81, 12, 41, 132, 78, skater9);
        SkaterSeason skaterSeason92 = new SkaterSeason(SEASON2, 82, 47, 41, 286, 36, skater9);
        SkaterSeason skaterSeason93 = new SkaterSeason(SEASON3, 81, 27, 43, 204, 52, skater9);

        SkaterSeason skaterSeason101 = new SkaterSeason(SEASON1, 82, 17, 39, 130, 139, skater10);
        SkaterSeason skaterSeason102 = new SkaterSeason(SEASON2, 69, 26, 30, 209, 52, skater10);
        SkaterSeason skaterSeason103 = new SkaterSeason(SEASON3, 82, 28, 41, 208, 46, skater10);

        skater1.setSeasons(Sets.newHashSet(skaterSeason11, skaterSeason12, skaterSeason13));
        skater2.setSeasons(Sets.newHashSet(skaterSeason21, skaterSeason22, skaterSeason23));
        skater3.setSeasons(Sets.newHashSet(skaterSeason31, skaterSeason32, skaterSeason33));
        skater4.setSeasons(Sets.newHashSet(skaterSeason41, skaterSeason42, skaterSeason43));
        skater5.setSeasons(Sets.newHashSet(skaterSeason51, skaterSeason52, skaterSeason53));
        skater6.setSeasons(Sets.newHashSet(skaterSeason61, skaterSeason62, skaterSeason63));
        skater7.setSeasons(Sets.newHashSet(skaterSeason71, skaterSeason72, skaterSeason73));
        skater8.setSeasons(Sets.newHashSet(skaterSeason81, skaterSeason82, skaterSeason83));
        skater9.setSeasons(Sets.newHashSet(skaterSeason91, skaterSeason92, skaterSeason93));
        skater10.setSeasons(Sets.newHashSet(skaterSeason101, skaterSeason102, skaterSeason103));

        this.skaterRepository.save(skater1);
        this.skaterRepository.save(skater2);
        this.skaterRepository.save(skater3);
        this.skaterRepository.save(skater4);
        this.skaterRepository.save(skater5);
        this.skaterRepository.save(skater6);
        this.skaterRepository.save(skater7);
        this.skaterRepository.save(skater8);
        this.skaterRepository.save(skater9);
        this.skaterRepository.save(skater10);
    }

    /**
     * Loads all goalie data into the database for testing
     */
    private void loadGoalieData() {
        Goalie goalie1 = new Goalie("Cain", "Morrow");
        Goalie goalie2 = new Goalie("Gordon", "Acosta");
        Goalie goalie3 = new Goalie("Reid", "Wardle");
        Goalie goalie4 = new Goalie("Jacob", "Neale");
        Goalie goalie5 = new Goalie("Angel", "Melton");
        Goalie goalie6 = new Goalie("Jared", "Bloggs");
        Goalie goalie7 = new Goalie("Zac", "Reed");
        Goalie goalie8 = new Goalie("Paul", "Cornish");

        GoalieSeason goalieSeason13 = new GoalieSeason(SEASON1, 39, 39, 27, 4, 0, 893, 953, 60, 8, goalie1);
        GoalieSeason goalieSeason12 = new GoalieSeason(SEASON2, 28, 28, 20, 4, 0, 625, 670, 45, 7, goalie1);
        GoalieSeason goalieSeason11 = new GoalieSeason(SEASON3, 28, 28, 24, 4, 0, 625, 656, 31, 10, goalie1);

        GoalieSeason goalieSeason21 = new GoalieSeason(SEASON1, 21, 21, 15, 4, 0, 394, 420, 26, 6, goalie2);
        GoalieSeason goalieSeason22 = new GoalieSeason(SEASON2, 24, 24, 20, 3, 0, 464, 491, 27, 9, goalie2);
        GoalieSeason goalieSeason23 = new GoalieSeason(SEASON3, 23, 23, 17, 4, 0, 420, 486, 28, 7, goalie2);

        GoalieSeason goalieSeason31 = new GoalieSeason(SEASON1, 39, 39, 25, 7, 0, 794, 855, 61, 5, goalie3);
        GoalieSeason goalieSeason32 = new GoalieSeason(SEASON2, 18, 18, 10, 6, 0, 358, 385, 58, 8, goalie3);
        GoalieSeason goalieSeason33 = new GoalieSeason(SEASON3, 40, 40, 28, 6, 0, 710, 755, 45, 11, goalie3);

        GoalieSeason goalieSeason41 = new GoalieSeason(SEASON1, 34, 34, 24, 7, 0, 702, 747, 45, 8, goalie4);
        GoalieSeason goalieSeason42 = new GoalieSeason(SEASON2, 51, 51, 16, 26, 0, 1377, 1487, 110, 6, goalie4);
        GoalieSeason goalieSeason43 = new GoalieSeason(SEASON3, 40, 40, 19, 18, 0, 1025, 1236, 79, 7, goalie4);

        GoalieSeason goalieSeason51 = new GoalieSeason(SEASON1, 55, 55, 20, 27, 0, 1354, 1480, 126, 2, goalie5);
        GoalieSeason goalieSeason52 = new GoalieSeason(SEASON2, 47, 47, 23, 16, 0, 1298, 1401, 103, 4, goalie5);
        GoalieSeason goalieSeason53 = new GoalieSeason(SEASON3, 50, 50, 38, 9, 2, 1355, 1443, 88, 8, goalie5);

        GoalieSeason goalieSeason61 = new GoalieSeason(SEASON1, 54, 54, 21, 26, 0, 1356, 1453, 97, 7, goalie6);
        GoalieSeason goalieSeason62 = new GoalieSeason(SEASON2, 52, 52, 28, 17, 0, 1359, 1473, 114, 3, goalie6);
        GoalieSeason goalieSeason63 = new GoalieSeason(SEASON3, 48, 48, 25, 20, 0, 1324, 1417, 93, 4, goalie6);

        GoalieSeason goalieSeason71 = new GoalieSeason(SEASON1, 21, 21, 7, 9, 0, 520, 564, 44, 3, goalie7);
        GoalieSeason goalieSeason72 = new GoalieSeason(SEASON2, 38, 38, 19, 16, 0, 942, 1010, 68, 3, goalie7);
        GoalieSeason goalieSeason73 = new GoalieSeason(SEASON3, 29, 29, 15, 8, 0, 757, 986, 55, 3, goalie7);

        GoalieSeason goalieSeason81 = new GoalieSeason(SEASON1, 35, 35, 15, 14, 0, 962, 1031, 69, 4, goalie8);
        GoalieSeason goalieSeason82 = new GoalieSeason(SEASON2, 28, 28, 13, 10, 0, 785, 843, 58, 10, goalie8);
        GoalieSeason goalieSeason83 = new GoalieSeason(SEASON3, 46, 46, 21, 18, 0, 1269, 1361, 92, 5, goalie8);

        goalie1.setSeasons(Sets.newHashSet(goalieSeason11, goalieSeason12, goalieSeason13));
        goalie2.setSeasons(Sets.newHashSet(goalieSeason21, goalieSeason22, goalieSeason23));
        goalie3.setSeasons(Sets.newHashSet(goalieSeason31, goalieSeason32, goalieSeason33));
        goalie4.setSeasons(Sets.newHashSet(goalieSeason41, goalieSeason42, goalieSeason43));
        goalie5.setSeasons(Sets.newHashSet(goalieSeason51, goalieSeason52, goalieSeason53));
        goalie6.setSeasons(Sets.newHashSet(goalieSeason61, goalieSeason62, goalieSeason63));
        goalie7.setSeasons(Sets.newHashSet(goalieSeason71, goalieSeason72, goalieSeason73));
        goalie8.setSeasons(Sets.newHashSet(goalieSeason81, goalieSeason82, goalieSeason83));

        this.goalieRepository.save(goalie1);
        this.goalieRepository.save(goalie2);
        this.goalieRepository.save(goalie3);
        this.goalieRepository.save(goalie4);
        this.goalieRepository.save(goalie5);
        this.goalieRepository.save(goalie6);
        this.goalieRepository.save(goalie7);
        this.goalieRepository.save(goalie8);
    }

    /**
     * Loads all team data into the database for testing
     */
    private void loadTeamData() {

        Team team1 = new Team("Victoria Seagulls");
        Team team2 = new Team("Louisiana Dementors");
        Team team3 = new Team("Idaho Truckers");
        Team team4 = new Team("Winchester Rafters");
        Team team5 = new Team("South Dakota Bangers");

        TeamSeason teamSeason11 = new TeamSeason(SEASON1, 82, 62, 16, 4, 325, 222, team1);
        TeamSeason teamSeason12 = new TeamSeason(SEASON2, 82, 33, 39, 10, 226, 271, team1);
        TeamSeason teamSeason13 = new TeamSeason(SEASON3, 82, 43, 32, 7, 210, 202, team1);

        TeamSeason teamSeason21 = new TeamSeason(SEASON1, 82, 32, 40, 10, 227, 277, team2);
        TeamSeason teamSeason22 = new TeamSeason(SEASON2, 82, 37, 37, 8, 244, 281, team2);
        TeamSeason teamSeason23 = new TeamSeason(SEASON3, 82, 38, 30, 14, 260, 246, team2);

        TeamSeason teamSeason31 = new TeamSeason(SEASON1, 82, 36, 34, 12, 270, 292, team3);
        TeamSeason teamSeason32 = new TeamSeason(SEASON2, 82, 29, 47, 6, 242, 302, team3);
        TeamSeason teamSeason33 = new TeamSeason(SEASON3, 82, 31, 42, 9, 202, 263, team3);

        TeamSeason teamSeason41 = new TeamSeason(SEASON1, 82, 39, 35, 8, 213, 223, team4);
        TeamSeason teamSeason42 = new TeamSeason(SEASON2, 82, 35, 36, 11, 225, 254, team4);
        TeamSeason teamSeason43 = new TeamSeason(SEASON3, 82, 45, 28, 9, 247, 223, team4);

        TeamSeason teamSeason51 = new TeamSeason(SEASON1, 82, 44, 26, 12, 273, 241, team5);
        TeamSeason teamSeason52 = new TeamSeason(SEASON2, 82, 47, 31, 4, 258, 232, team5);
        TeamSeason teamSeason53 = new TeamSeason(SEASON3, 82, 44, 30, 8, 249, 236, team5);

        team1.setSeasons(Sets.newHashSet(teamSeason11, teamSeason12, teamSeason13));
        team2.setSeasons(Sets.newHashSet(teamSeason21, teamSeason22, teamSeason23));
        team3.setSeasons(Sets.newHashSet(teamSeason31, teamSeason32, teamSeason33));
        team4.setSeasons(Sets.newHashSet(teamSeason41, teamSeason42, teamSeason43));
        team5.setSeasons(Sets.newHashSet(teamSeason51, teamSeason52, teamSeason53));

        this.teamRepository.save(team1);
        this.teamRepository.save(team2);
        this.teamRepository.save(team3);
        this.teamRepository.save(team4);
        this.teamRepository.save(team5);

    }
}
