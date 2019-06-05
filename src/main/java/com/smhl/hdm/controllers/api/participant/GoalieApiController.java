package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.entities.participant.impl.GoalieFacade;
import com.smhl.hdm.facades.nonentities.statistics.impl.GoalieStatisticsFacade;
import com.smhl.hdm.resources.participant.impl.GoalieResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    public GoalieApiController(GoalieFacade goalieFacade, GoalieStatisticsFacade goalieStatisticsFacade) {
        this.goalieFacade = goalieFacade;
        this.goalieStatisticsFacade = goalieStatisticsFacade;
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
        return findParticipant(id, this.goalieFacade.find(id));
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
    public ResponseEntity<HdmApiResponse> getTopGoaliesForStatAndLimit(final @RequestParam String stat, final @RequestParam int limit) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieFacade.findTopGoaliesForStatAndLimit(stat, limit)), HttpStatus.OK);
    }

    /**
     * Obtains stats information for goalie attribute categories
     *
     * @param seasonString season that we're looking at
     * @return stat object response
     */
    @GetMapping("/stats-for-active")
    public ResponseEntity<HdmApiResponse> getStatisticsForActiveGoalies(final @RequestParam String seasonString) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieStatisticsFacade.obtainActiveStatistics(seasonString)), HttpStatus.OK);
    }
}
