package com.smhl.hdm.controllers.api;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.service.entities.seasonstring.SeasonStringService;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

/**
 * A high-level controller to provide information about the system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class HdmApiController extends AbstractHdmController {

    private SeasonStringService seasonStringService;

    @Autowired
    public HdmApiController(SeasonStringService seasonStringService) {
        this.seasonStringService = seasonStringService;
    }


    //  METHODS

    //  *************** GET ***************

    /**
     * Returns the version of the hdm API. Typically used to gauge whether the API is running
     *
     * @return HDM API version
     */
    @GetMapping("/")
    public ResponseEntity<HdmApiResponse> getApi() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, "HDM Version 0.1"), OK);
    }

    /**
     * Gets the current season as a string
     *
     * @return current season string, ex: 2018-2019
     */
    @GetMapping("/current-season")
    public ResponseEntity<HdmApiResponse> getCurrentSeasonString() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, HdmUtils.getCurrentSeasonString()), OK);
    }

    /**
     * Gets a list of all the available season strings in the system. In this case, the list contains seasons for which we have saved games
     *
     * @return list of season strings
     */
    @GetMapping("/season-strings")
    public ResponseEntity<HdmApiResponse> getSeasonStrings() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.seasonStringService.getAllSeasonStrings()), OK);
    }
}
