package com.smhl.hdm.resources.game;

import com.smhl.hdm.resources.HdmResource;
import com.smhl.hdm.resources.details.game.GameDetailsResource;
import com.smhl.hdm.resources.participant.impl.TeamResource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;

/**
 * A DTO for Games
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class GameResource implements HdmResource {

    @Getter
    @Setter
    private Long code;

    @Getter
    @Setter
    private LocalDateTime gameTime;

    @Getter
    @Setter
    private String seasonString;

    @Getter
    @Setter
    private String gameStatus;

    @Getter
    @Setter
    private TeamResource homeTeam;

    @Getter
    @Setter
    private TeamResource awayTeam;

    @Getter
    @Setter
    private GameDetailsResource gameDetails;


    //  METHODS

    @Override
    public boolean isPresent() {
        return
                this.code != null &&
                StringUtils.isNotEmpty(this.seasonString) &&
                StringUtils.isNotEmpty(this.gameStatus) &&
                this.homeTeam.isPresent() &&
                this.awayTeam.isPresent();
    }
}
