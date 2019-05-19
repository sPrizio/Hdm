package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.models.participant.impl.Goalie;
import com.smhl.hdm.service.participant.impl.GoalieService;
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
public class GoalieApiController extends AbstractHdmController<Goalie> {

    private GoalieService goalieService;

    @Autowired
    public GoalieApiController(GoalieService goalieService) {
        this.goalieService = goalieService;
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
        return findParticipant(id, this.goalieService.find(id));
    }

    /**
     * Finds all the goalies in the system
     *
     * @return list of all goalies
     */
    @GetMapping("/all")
    public ResponseEntity<HdmApiResponse> getAllGoalies() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieService.findAll()), HttpStatus.OK);
    }

    /**
     * Gets all active goalies in the system
     *
     * @return list of goalies that are marked as active
     */
    @GetMapping("/all-active")
    public ResponseEntity<HdmApiResponse> getAllActiveGoalies(final @RequestParam String field, final @RequestParam String order) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.goalieService.getAllActiveParticipants(field, order)), HttpStatus.OK);
    }
}
