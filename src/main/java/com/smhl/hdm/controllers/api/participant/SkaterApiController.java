package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.participant.impl.SkaterFacade;
import com.smhl.hdm.resources.participant.impl.SkaterResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that exposes various endpoints for information about Skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/skater")
public class SkaterApiController extends AbstractHdmController<SkaterResource> {

    private SkaterFacade skaterFacade;

    @Autowired
    public SkaterApiController(SkaterFacade skaterFacade) {
        this.skaterFacade = skaterFacade;
    }


    //  METHODS

    /**
     * Finds a skater for the given id
     *
     * @param id id of skater
     * @return http response with data based on whether the skater was found
     */
    @GetMapping("/{id}")
    public ResponseEntity<HdmApiResponse> getSkater(final @PathVariable("id") Long id) {
        return findParticipant(id, this.skaterFacade.find(id));
    }

    /**
     * Finds all the skaters in the system
     *
     * @return list of all skaters
     */
    @GetMapping("/all")
    public ResponseEntity<HdmApiResponse> getAllSkaters() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterFacade.findAll()), HttpStatus.OK);
    }

    /**
     * Gets all active skaters in the system
     *
     * @return list of skaters that are marked as active
     */
    @GetMapping("/all-active")
    public ResponseEntity<HdmApiResponse> getAllActiveSkaters(final @RequestParam String seasonString, final @RequestParam String field, final @RequestParam String order) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterFacade.findAllParticipantsForSeason(seasonString, field, order)), HttpStatus.OK);
    }
}
