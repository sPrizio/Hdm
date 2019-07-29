package com.smhl.hdm.controllers.api.participant;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.entities.game.GameFacade;
import com.smhl.hdm.facades.entities.participant.impl.SkaterFacade;
import com.smhl.hdm.facades.nonentities.milestone.impl.SkaterMilestoneFacade;
import com.smhl.hdm.facades.nonentities.statistics.impl.SkaterStatisticsFacade;
import com.smhl.hdm.models.nonentities.Milestone;
import com.smhl.hdm.models.nonentities.Statistic;
import com.smhl.hdm.resources.details.participant.SkaterGameDetailsResource;
import com.smhl.hdm.resources.participant.impl.SkaterResource;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.impl.participant.SkaterValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    private SkaterValidator skaterValidator;
    private GameFacade gameFacade;
    private SkaterMilestoneFacade skaterMilestoneFacade;

    @Autowired
    public SkaterApiController(SkaterFacade skaterFacade, SkaterStatisticsFacade skaterStatisticsFacade, SkaterValidator skaterValidator, GameFacade gameFacade, SkaterMilestoneFacade skaterMilestoneFacade) {
        this.skaterFacade = skaterFacade;
        this.skaterStatisticsFacade = skaterStatisticsFacade;
        this.skaterValidator = skaterValidator;
        this.gameFacade = gameFacade;
        this.skaterMilestoneFacade = skaterMilestoneFacade;
    }


    //  METHODS

    //  *************** GET ***************

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
    public ResponseEntity<HdmApiResponse> getTopSkatersForStatAndLimit(final @RequestParam String stat, final @RequestParam Integer limit) {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterFacade.findTopSkatersForStatAndLimit(stat, limit)), HttpStatus.OK);
    }

    /**
     * Returns a statistics object that contains statistical information for a skater's stat categories
     *
     * @param id skater id
     * @return skater career statistical information
     */
    @GetMapping("/stats-for-skater/{id}")
    public ResponseEntity<HdmApiResponse> getStatisticsForSkater(final @PathVariable("id") Long id) {

        Statistic statistic = this.skaterStatisticsFacade.obtainStatistics(id);

        if (!statistic.isEmpty()) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterStatisticsFacade.obtainStatistics(id)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Skater could not be found"), HttpStatus.OK);
    }

    /**
     * Returns a statistics object that contains statistical information for various skater stat attribute categories
     *
     * @param seasonString season of which we want to obtain the stats
     * @return stat object
     */
    @GetMapping("/stats-for-active")
    public ResponseEntity<HdmApiResponse> getStatisticsForActiveSkaters(final @RequestParam String seasonString) {

        Statistic statistic = this.skaterStatisticsFacade.obtainActiveStatistics(seasonString);

        if (!statistic.isEmpty()) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.skaterStatisticsFacade.obtainActiveStatistics(seasonString)), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Internal Server Error"), HttpStatus.OK);
    }

    /**
     * Returns a collection of recent games for the given skater
     *
     * @param id    skater id
     * @param limit number of games to be returned in the list
     * @return a collection of recent games this participant was involved in
     */
    @GetMapping("/recent-games/{id}")
    public ResponseEntity<HdmApiResponse> getRecentGamesForSkater(final @PathVariable("id") Long id, final @RequestParam Integer limit) {

        List<SkaterGameDetailsResource> games = this.gameFacade.findRecentGameDetailsForSkater(id, limit);

        if (CollectionUtils.isNotEmpty(games)) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, games), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "No game details were found for the given skater id"), HttpStatus.OK);
    }

    /**
     * Returns a milestone object for the given stat
     *
     * @param id   skater id
     * @param stat stat on which we wish to obtain a milestone
     * @return milestone object
     */
    @GetMapping("/milestone/{id}")
    public ResponseEntity<HdmApiResponse> getMilestoneForSkater(final @PathVariable("id") Long id, final @RequestParam String stat) {

        Milestone milestone = this.skaterMilestoneFacade.obtainMilestone(stat, id);

        if (milestone.isPresent()) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, milestone), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "No milestone could be obtained for the given skater id"), HttpStatus.OK);
    }


    //  *************** POST ***************

    /**
     * Creates a new skater in the system
     *
     * @param params request params containing information for a new skater
     * @return newly created skater
     */
    @PostMapping("/create")
    public ResponseEntity<HdmApiResponse> createSkater(final @RequestBody Map<String, Object> params) {

        ValidationResult result = this.skaterValidator.validate(params);

        if (result.isValid()) {

            SkaterResource resource = this.skaterFacade.create(params);

            if (resource != null) {
                return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, resource), HttpStatus.OK);
            }

            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Skater could not be created"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, result), HttpStatus.OK);
    }
}
