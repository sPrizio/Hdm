package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.entities.game.GameFacade;
import com.smhl.hdm.facades.entities.participant.impl.GoalieFacade;
import com.smhl.hdm.facades.nonentities.milestone.impl.GoalieMilestoneFacade;
import com.smhl.hdm.facades.nonentities.statistics.impl.GoalieStatisticsFacade;
import com.smhl.hdm.models.nonentities.Milestone;
import com.smhl.hdm.models.nonentities.Statistic;
import com.smhl.hdm.resources.details.participant.GoalieGameDetailsResource;
import com.smhl.hdm.resources.participant.impl.GoalieResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller that exposes various endpoints for information about goalies
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/goalie")
public class GoalieApiController extends AbstractHdmController<GoalieResource> {

    private GoalieFacade goalieFacade;
    private GoalieStatisticsFacade goalieStatisticsFacade;
    private GameFacade gameFacade;
    private GoalieMilestoneFacade goalieMilestoneFacade;

    @Autowired
    public GoalieApiController(GoalieFacade goalieFacade, GoalieStatisticsFacade goalieStatisticsFacade, GameFacade gameFacade, GoalieMilestoneFacade goalieMilestoneFacade) {
        this.goalieFacade = goalieFacade;
        this.goalieStatisticsFacade = goalieStatisticsFacade;
        this.gameFacade = gameFacade;
        this.goalieMilestoneFacade = goalieMilestoneFacade;
    }


    //  METHODS

    /**
     * Finds a goalie for the given id
     *
     * @param id id of goalie
     * @return http response with data based on whether the goalie was found
     */
    @GetMapping("/{id}")
    public ResponseEntity<HdmApiResponse> getGoalie(final @PathVariable("id") Long id) {
        return findEntity(id, this.goalieFacade.find(id));
    }

    /**
     * Finds all the goalies in the system
     *
     * @return list of all goalies
     */
    @GetMapping("/all")
    public ResponseEntity<HdmApiResponse> getAllGoalies() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieFacade.findAll()), HttpStatus.OK);
    }

    /**
     * Gets all active goalies in the system
     *
     * @return list of goalies that are marked as active
     */
    @GetMapping("/all-active")
    public ResponseEntity<HdmApiResponse> getAllActiveGoalies(final @RequestParam String seasonString, final @RequestParam String field, final @RequestParam String order) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieFacade.findAllParticipantsForSeason(seasonString, field, order)), HttpStatus.OK);
    }

    /**
     * Gets top goalies for a given stat
     *
     * @param stat  stat to rank goalies
     * @param limit limit number of results
     * @return limited list of goalies for a given stat
     */
    @GetMapping("/top-active")
    public ResponseEntity<HdmApiResponse> getTopGoaliesForStatAndLimit(final @RequestParam String stat, final @RequestParam Integer limit) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieFacade.findTopGoaliesForStatAndLimit(stat, limit)), HttpStatus.OK);
    }

    /**
     * Returns a statistics object that contains statistical information for a goalie's stat categories
     *
     * @param id goalie id
     * @return goalie career statistical information
     */
    @GetMapping("/stats-for-goalie/{id}")
    public ResponseEntity<HdmApiResponse> getStatisticsForGoalie(final @PathVariable Long id) {

        Statistic statistic = this.goalieStatisticsFacade.obtainStatistics(id);

        if (!statistic.isEmpty()) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieStatisticsFacade.obtainStatistics(id)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Goalie could not be found"), HttpStatus.OK);
    }

    /**
     * Obtains stats information for goalie attribute categories
     *
     * @param seasonString season that we're looking at
     * @return stat object response
     */
    @GetMapping("/stats-for-active")
    public ResponseEntity<HdmApiResponse> getStatisticsForActiveGoalies(final @RequestParam String seasonString) {

        Statistic statistic = this.goalieStatisticsFacade.obtainActiveStatistics(seasonString);

        if (!statistic.isEmpty()) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieStatisticsFacade.obtainActiveStatistics(seasonString)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Internal Server Error"), HttpStatus.OK);
    }

    /**
     * Returns a collection of recent games for the given goalie
     *
     * @param id    goalie id
     * @param limit number of games to be returned in the list
     * @return a collection of recent games this participant was involved in
     */
    @GetMapping("/recent-games/{id}")
    public ResponseEntity<HdmApiResponse> getRecentGamesForGoalie(final @PathVariable("id") Long id, final @RequestParam Integer limit) {

        List<GoalieGameDetailsResource> games = this.gameFacade.findRecentGameDetailsForGoalie(id, limit);

        if (CollectionUtils.isNotEmpty(games)) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, games), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "No game details were found for the given goalie id"), HttpStatus.OK);
    }

    /**
     * Gets a milestone object for the given goalie and stat attribute
     *
     * @param id   goalie id
     * @param stat stat we want to look at
     * @return milestone object
     */
    @GetMapping("/milestone/{id}")
    public ResponseEntity<HdmApiResponse> getMilestoneForGoalie(final @PathVariable("id") Long id, final @RequestParam String stat) {

        Milestone milestone = this.goalieMilestoneFacade.obtainMilestone(stat, id);

        if (milestone.isPresent()) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, milestone), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "No milestone could be obtained for the given goalie id"), HttpStatus.OK);
    }
}
