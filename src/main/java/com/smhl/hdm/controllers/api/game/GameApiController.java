package com.smhl.hdm.controllers.api.game;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.entities.game.GameFacade;
import com.smhl.hdm.resources.game.GameResource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controls various endpoints for Game-related information retrieval
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@RestController
@RequestMapping("/api/game")
public class GameApiController extends AbstractHdmController<GameResource> {

    private GameFacade gameFacade;

    @Autowired
    public GameApiController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }


    //  METHODS

    /**
     * Finds a game for the given id
     *
     * @param id game id
     * @return game matching id
     */
    @GetMapping("/{id}")
    public ResponseEntity<HdmApiResponse> getGame(final @PathVariable("id") Long id) {
        return findEntity(id, this.gameFacade.find(id));
    }

    /**
     * Finds all games in the system
     *
     * @return list of all games in system
     */
    @GetMapping("/all")
    public ResponseEntity<HdmApiResponse> getAllGames() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.gameFacade.findAll()), HttpStatus.OK);
    }

    /**
     * Finds all games for a given season
     * @param seasonString season on which we want to see games
     * @return list of games for a particular season
     */
    @GetMapping("/all-for-season")
    public ResponseEntity<HdmApiResponse> getAllGamesForSeason(final @RequestParam String seasonString) {

        List<GameResource> resources = this.gameFacade.findForSeason(seasonString);

        if (CollectionUtils.isEmpty(resources)) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "No games found for given season"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, resources), HttpStatus.OK);
    }

    /**
     * Finds the most recent game that is complete for the current season
     *
     * @return most recently completed game
     */
    @GetMapping
    public ResponseEntity<HdmApiResponse> getLatestCompletedGame() {

        GameResource resource = this.gameFacade.findLatestCompletedGame();

        if (resource.isPresent()) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, resource), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "No recently completed game found"), HttpStatus.OK);
    }

    /**
     * Finds the 3 stars for a game
     *
     * @param id game that we're looking at
     * @return list of 3 top players for a game
     */
    @GetMapping("/{id}/three-stars")
    public ResponseEntity<HdmApiResponse> getThreeStarsForGame(final @PathVariable("id") Long id) {

        ResponseEntity<HdmApiResponse> response = findEntity(id, this.gameFacade.find(id));

        if (response.getBody() == null || response.getBody().getResponse() == null || response.getBody().getResponse().equals(HdmApiResponseResult.FAILURE)) {
            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Could not identify 3 stars for the given game id"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, this.gameFacade.find3StarsForGame((GameResource) response.getBody().getData())), HttpStatus.OK);
    }
}
