package com.smhl.hdm.translators.participant;

import com.smhl.hdm.models.entities.participant.impl.Skater;
import com.smhl.hdm.translators.HdmTranslator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Implementation of a translator for Skater. Refer to the interface for more information
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SkaterTranslator implements HdmTranslator<Skater> {

    @Override
    public Skater translate(Map<String, Object> values) {
        return new Skater(
                values.get("firstName").toString(),
                values.get("lastName").toString(),
                values.get("position").toString(),
                Boolean.parseBoolean(values.get("active").toString())
        );
    }
}
