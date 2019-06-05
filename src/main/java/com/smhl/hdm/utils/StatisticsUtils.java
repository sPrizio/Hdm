package com.smhl.hdm.utils;

import com.smhl.hdm.models.entities.season.Season;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Utility classes for statistics calculations
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
public class StatisticsUtils<E extends Season> {

    /**
     * Obtains numerical statistics for a list of objects given a custom collector
     *
     * @param key attribute on which we wish to aggregate information
     * @param collector custom collector to aggregate information
     * @param entities list of entities on which to compute information
     * @return summary statistics for an entity's attributes
     */
    public DoubleSummaryStatistics calculateStatistics(String key, Collector<E, ?, DoubleSummaryStatistics> collector, Collection<E> entities) {
        return
                entities
                        .parallelStream()
                        .collect(Collectors.groupingBy(entity -> key, collector))
                        .get(key);
    }
}
