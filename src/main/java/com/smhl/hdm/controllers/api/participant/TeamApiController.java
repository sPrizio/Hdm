package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.models.participant.impl.Team;
import com.smhl.hdm.service.participant.impl.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that exposes various endpoints for information about teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/teams")
public class TeamApiController extends AbstractHdmController<Team> {

    private TeamService teamService;

    @Autowired
    public TeamApiController(TeamService teamService) {
        this.teamService = teamService;
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
        return findParticipant(id, this.teamService.find(id));
    }

    /**
     * Finds all the teams in the system
     *
     * @return list of all teams
     */
    @GetMapping("/all")
    public ResponseEntity<HdmApiResponse> getAllSkaters() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.teamService.findAll()), HttpStatus.OK);
    }

    /**
     * Gets all active teams in the system
     *
     * @return list of teams  that are marked as active
     */
    @GetMapping("/all-active")
    public ResponseEntity<HdmApiResponse> getAllActiveSkaters() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.teamService.getAllActiveParticipants()), HttpStatus.OK);
    }
}
