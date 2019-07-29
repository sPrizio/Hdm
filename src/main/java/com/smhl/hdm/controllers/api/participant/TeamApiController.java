package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.entities.participant.impl.TeamFacade;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.impl.participant.TeamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private TeamValidator teamValidator;

    @Autowired
    public TeamApiController(TeamFacade teamFacade, TeamValidator teamValidator) {
        this.teamFacade = teamFacade;
        this.teamValidator = teamValidator;
    }


    //  METHODS

    //  *************** GET ***************

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


    //  *************** POST ***************

    /**
     * Creates a new team in the system
     *
     * @param params request params containing information for a new team
     * @return newly created team
     */
    @PostMapping("/create")
    public ResponseEntity<HdmApiResponse> createSkater(final @RequestBody Map<String, Object> params) {

        ValidationResult result = this.teamValidator.validate(params);

        if (result.isValid()) {

            TeamResource resource = this.teamFacade.create(params);

            if (resource != null) {
                return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, resource), HttpStatus.OK);
            }

            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Team could not be created"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, result), HttpStatus.OK);
    }
}
