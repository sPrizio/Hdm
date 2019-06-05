package com.smhl.hdm.service.statistic;

import com.smhl.hdm.models.entities.participant.Participant;
import com.smhl.hdm.models.nonentities.Statistic;

import java.util.List;

/**
 * Parent-level statistic interface for obtaining statistic for a particular entity
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
public interface StatisticsService<P extends Participant> {

    Statistic calculate(P entity);

    Statistic calculate(List<P> entities, String seasonString);
}
