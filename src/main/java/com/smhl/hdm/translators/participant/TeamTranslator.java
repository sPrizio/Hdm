package com.smhl.hdm.translators.participant;

import com.smhl.hdm.models.entities.participant.impl.Team;
import com.smhl.hdm.translators.HdmTranslator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Implementation of a translator for Team. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class TeamTranslator implements HdmTranslator<Team> {

    @Override
    public Team translate(Map<String, Object> values) {
        return new Team(
                values.get("name").toString(),
                Boolean.parseBoolean(values.get("active").toString())
        );
    }
}
