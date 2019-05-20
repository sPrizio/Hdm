package com.smhl.hdm.models.seasonstring;

import lombok.*;

import javax.persistence.*;

/**
 * This is a unique class that means to track the seasons since the system's inception
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class SeasonString {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column
    @NonNull
    private String season;
}
