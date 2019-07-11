package com.smhl.hdm.service.entities.season.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.models.entities.season.impl.SkaterSeason;
import com.smhl.hdm.repositories.season.SkaterSeasonRepository;
import com.smhl.hdm.service.entities.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the season service for skaters. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class SkaterSeasonService implements SeasonService<SkaterSeason> {

    private SkaterSeasonRepository skaterSeasonRepository;

    @Autowired
    public SkaterSeasonService(SkaterSeasonRepository skaterSeasonRepository) {
        this.skaterSeasonRepository = skaterSeasonRepository;
    }


    //  METHODS

    @Override
    public void refresh(SkaterSeason skaterSeason) {
        this.skaterSeasonRepository.refresh(skaterSeason);
    }

    @Override
    public Optional<SkaterSeason> find(Long id) {
        return this.skaterSeasonRepository.findById(id);
    }

    @Override
    public List<SkaterSeason> findAll() {
        return Lists.newArrayList(this.skaterSeasonRepository.findAll());
    }

    @Override
    public SkaterSeason save(SkaterSeason entity) {
        return this.skaterSeasonRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

        if (find(id).isPresent()) {
            this.skaterSeasonRepository.deleteById(id);
        }
    }

    @Override
    public SkaterSeason create(Map<String, Object> params) {
        return null;
    }
}
