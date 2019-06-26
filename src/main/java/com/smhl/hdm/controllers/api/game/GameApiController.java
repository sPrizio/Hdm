package com.smhl.hdm.controllers.api.game;

import com.smhl.hdm.controllers.AbstractHdmController;
import com.smhl.hdm.controllers.response.HdmApiResponse;
import com.smhl.hdm.enums.GameStatus;
import com.smhl.hdm.enums.HdmApiResponseResult;
import com.smhl.hdm.facades.entities.game.GameFacade;
import com.smhl.hdm.resources.game.GameResource;
import com.smhl.hdm.validation.result.ValidationResult;
import com.smhl.hdm.validation.validator.impl.details.game.GameDetailsValidator;
import com.smhl.hdm.validation.validator.impl.game.GameValidator;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private GameValidator gameValidator;
    private GameDetailsValidator gameDetailsValidator;

    @Autowired
    public GameApiController(GameFacade gameFacade, GameValidator gameValidator, GameDetailsValidator gameDetailsValidator) {
        this.gameFacade = gameFacade;
        this.gameValidator = gameValidator;
        this.gameDetailsValidator = gameDetailsValidator;
    }


    //  METHODS

    //  *************** GET ***************

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
     * @param id if od the game that we're looking at
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

    /**
     * Returns a list of the possible states that a game can be in
     *
     * @return array list of game status values
     */
    @GetMapping("/status-enums")
    public ResponseEntity<HdmApiResponse> getGameStates() {
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, Arrays.stream(GameStatus.values()).map(Enum::name).collect(Collectors.toList())), HttpStatus.OK);
    }


    //  *************** POST ***************

    /**
     * Creates a new game in the system
     *
     * @param params request params containing information for a new game
     * @return newly created game
     */
    @PostMapping(value = "/create")
    public ResponseEntity<HdmApiResponse> createGame(final @RequestBody Map<String, Object> params) {

        ValidationResult result = this.gameValidator.validate(params);

        if (result.isValid()) {

            GameResource resource = this.gameFacade.create(params);

            if (resource != null) {
                return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, resource), HttpStatus.OK);
            }

            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Game could not be created"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, result), HttpStatus.OK);
    }

    /**
     * Completes a game. In this case we refer to completing a game as obtaining all of its statistics and computing their
     * values for use with participant and game stats
     *
     * @param id game id
     * @param params stats
     * @return game with updated stats
     */
    @PostMapping(value = "/{id}/complete")
    public ResponseEntity<HdmApiResponse> completeGame(final @PathVariable("id") Long id, final @RequestBody Map<String, Object> params) {

        ValidationResult result = this.gameDetailsValidator.validate(params);

        if (result.isValid()) {

            GameResource resource = this.gameFacade.complete(id, params);

            if (resource != null) {
                return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, resource), HttpStatus.OK);
            }

            return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, "Game could not be completed"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.FAILURE, result), HttpStatus.OK);
    }


    //  *************** DELETE ***************

    /**
     * Deletes a game by its id. Based on our data model, all related details classes and scoring plays
     * will also be deleted
     *
     * @param id game id
     * @return result of the deletion
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HdmApiResponse> deleteGame(final @PathVariable("id") Long id) {
        this.gameFacade.delete(id);
        return new ResponseEntity<>(new HdmApiResponse(HdmApiResponseResult.SUCCESS, "Deleted game with id " + id), HttpStatus.OK);
    }
}
