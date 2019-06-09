package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.entities.participant.impl.TeamFacade;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that exposes various endpoints for information about teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/team")
public class TeamApiController extends AbstractHdmController<TeamResource> {

    private TeamFacade teamFacade;

    @Autowired
    public TeamApiController(TeamFacade teamFacade) {
        this.teamFacade = teamFacade;
    }


    //  METHODS

    /**
     * Finds a team for the given id
     *
     * @param id id of team
     * @return http response with data based on whether the team was found
     */
    @GetMapping("/{id}")
    public ResponseEntity<HdmApiResponse> getSkater(final @PathVariable("id") Long id) {
        return findEntity(id, this.teamFacade.find(id));
    }

    /**
     * Finds all the teams in the system
     *
     * @return list of all teams
     */
    @GetMapping("/all")
    public ResponseEntity<HdmApiResponse> getAllSkaters() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.teamFacade.findAll()), HttpStatus.OK);
    }

    /**
     * Gets all active teams in the system
     *
     * @return list of teams  that are marked as active
     */
    @GetMapping("/all-active")
    public ResponseEntity<HdmApiResponse> getAllActiveSkaters(final @RequestParam String seasonString, final @RequestParam String field, final @RequestParam String order) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.teamFacade.findAllParticipantsForSeason(seasonString, field, order)), HttpStatus.OK);
    }
}
