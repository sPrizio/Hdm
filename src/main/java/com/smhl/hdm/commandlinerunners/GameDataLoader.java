package com.smhl.hdm.commandlinerunners;

import com.smhl.hdm.repositories.game.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Loads test data for Games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 */
@Component
@Order(2)
public class GameDataLoader implements CommandLineRunner {

    private GameRepository gameRepository;

    @Autowired
    public GameDataLoader(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void run(String... args) {
        //  TODO: implement test data for Games
    }
}
