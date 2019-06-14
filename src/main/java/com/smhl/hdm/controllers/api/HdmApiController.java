package com.smhl.hdm.controllers.api;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.models.nonentities.star.impl.WeeklyStar;
import com.smhl.hdm.service.entities.seasonstring.SeasonStringService;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

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


    @GetMapping("/current-season")
    public ResponseEntity<HdmApiResponse> getCurrentSeasonString() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, HdmUtils.getCurrentSeasonString()), OK);
    }

    @GetMapping("/season-strings")
    public ResponseEntity<HdmApiResponse> getSeasonStrings() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.seasonStringService.getAllSeasonStrings()), OK);
    }

    @GetMapping("/three-stars")
    public ResponseEntity<HdmApiResponse> getWeeklyThreeStars(final @RequestParam LocalDateTime week) {
        //  TODO: implement weekly 3 stars
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, new ArrayList<WeeklyStar>()), OK);
    }
}
