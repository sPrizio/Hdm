package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.entities.participant.impl.SkaterFacade;
import com.smhl.hdm.facades.nonentities.statistics.impl.SkaterStatisticsFacade;
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
    private SkaterStatisticsFacade skaterStatisticsFacade;

    @Autowired
    public SkaterApiController(SkaterFacade skaterFacade, SkaterStatisticsFacade skaterStatisticsFacade) {
        this.skaterFacade = skaterFacade;
        this.skaterStatisticsFacade = skaterStatisticsFacade;
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
        return findEntity(id, this.skaterFacade.find(id));
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
     * Gets all active skaters in the system sorted by a particular field
     *
     * @param seasonString season to consider
     * @param field        field upon which to sort the results
     * @param order        sort order
     * @return list of skaters that are marked as active sorted by a field
     */
    @GetMapping("/all-active")
    public ResponseEntity<HdmApiResponse> getAllActiveSkaters(final @RequestParam String seasonString, final @RequestParam String field, final @RequestParam String order) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterFacade.findAllParticipantsForSeason(seasonString, field, order)), HttpStatus.OK);
    }

    /**
     * Returns the top active skaters for a stat and limits the number of results
     *
     * @param stat  stat on which to obtain skaters
     * @param limit integer limit of the result list
     * @return limited list sorted based on a stat
     */
    @GetMapping("/top-active")
    public ResponseEntity<HdmApiResponse> getTopSkatersForStatAndLimit(final @RequestParam String stat, final @RequestParam int limit) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterFacade.findTopSkatersForStatAndLimit(stat, limit)), HttpStatus.OK);
    }

    /**
     * Returns a statistics object that contains statistical information for various skater stat attribute categories
     *
     * @param seasonString season of which we want to obtain the stats
     * @return stat object
     */
    @GetMapping("/stats-for-active")
    public ResponseEntity<HdmApiResponse> getStatisticsForActiveSkaters(final @RequestParam String seasonString) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterStatisticsFacade.obtainActiveStatistics(seasonString)), HttpStatus.OK);
    }
}
