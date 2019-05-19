package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.models.participant.impl.Skater;
import com.smhl.hdm.service.participant.impl.SkaterService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller that exposes various endpoints for information about Skaters
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/skater")
public class SkaterApiController extends AbstractHdmController<Skater> {

    private SkaterService skaterService;

    @Autowired
    public SkaterApiController(SkaterService skaterService) {
        this.skaterService = skaterService;
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
        return findParticipant(id, this.skaterService.find(id));
    }

    /**
     * Finds all the skaters in the system
     *
     * @return list of all skaters
     */
    @GetMapping("/all")
    public ResponseEntity<HdmApiResponse> getAllSkaters() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterService.findAll()), HttpStatus.OK);
    }

    /**
     * Gets all active skaters in the system
     *
     * @return list of skaters that are marked as active
     */
    @GetMapping("/all-active")
    public ResponseEntity<HdmApiResponse> getAllActiveSkaters(final @RequestParam String field, final @RequestParam String order) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterService.getAllActiveParticipants(field, order)), HttpStatus.OK);
    }
}
