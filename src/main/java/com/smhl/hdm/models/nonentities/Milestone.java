package com.smhl.hdm.models.nonentities;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * A class representation of a milestone. In this case a milestone is a statistical plateau that a participant is close to reaching
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
@RequiredArgsConstructor
public class Milestone {

    @Getter
    @Setter
    @NonNull
    private String name;

    @Getter
    @Setter
    @NonNull
    private Integer value;

    @Getter
    @Setter
    @NonNull
    private Integer plateau;


    public boolean isPresent() {
        return StringUtils.isNotEmpty(this.name) && this.value != null && this.plateau != null;
    }
}
