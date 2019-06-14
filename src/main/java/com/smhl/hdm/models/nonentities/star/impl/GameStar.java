package com.smhl.hdm.models.nonentities.star.impl;

import com.smhl.hdm.models.entities.details.Details;
import com.smhl.hdm.models.nonentities.star.Star;
import lombok.*;

/**
 * Class representation of a game star. A game star is a participant that performed at an above-average rate in a single game
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class GameStar<D extends Details> implements Star {

    @Getter
    @Setter
    @NonNull
    private D details;
}
