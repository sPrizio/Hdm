package com.smhl.hdm.translators.participant;

import com.smhl.hdm.models.entities.participant.impl.Goalie;
import com.smhl.hdm.translators.HdmTranslator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Implementation of a translator for Game. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class GoalieTranslator implements HdmTranslator<Goalie> {

    @Override
    public Goalie translate(Map<String, Object> values) {
        return new Goalie(
                values.get("firstName").toString(),
                values.get("lastName").toString(),
                Boolean.parseBoolean(values.get("active").toString())
        );
    }
}
