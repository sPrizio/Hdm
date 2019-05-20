package com.smhl.hdm.repositories.seasonstring;

import com.smhl.hdm.models.seasonstring.SeasonString;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO access-layer for SeasonString
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface SeasonStringRepository extends CrudRepository<SeasonString, Long> {
}
