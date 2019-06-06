package com.smhl.hdm.facades.entities.game;

import com.smhl.hdm.converters.game.GameConverter;
import com.smhl.hdm.facades.entities.HdmFacade;
import com.smhl.hdm.models.entities.game.Game;
import com.smhl.hdm.resources.game.GameResource;
import com.smhl.hdm.service.entities.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Facade layer for Games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GameFacade implements HdmFacade<GameResource> {

    private GameService gameService;
    private GameConverter gameConverter;

    @Autowired
    public GameFacade(GameService gameService, GameConverter gameConverter) {
        this.gameService = gameService;
        this.gameConverter = gameConverter;
    }


    @Override
    public GameResource find(Long id) {

        Optional<Game> game = this.gameService.find(id);

        if (game.isPresent()) {
            return this.gameConverter.convert(game.get());
        }

        return new GameResource();
    }

    @Override
    public List<GameResource> findAll() {
        return this.gameConverter.convertAll(this.gameService.findAll());
    }
}
