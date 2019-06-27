package com.smhl.hdm.service.entities.season.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.models.entities.season.impl.GoalieSeason;
import com.smhl.hdm.repositories.season.GoalieSeasonRepository;
import com.smhl.hdm.service.entities.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implements the season service for goalies. Documentation for the overridden methods can be located in the interface
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class GoalieSeasonService implements SeasonService<GoalieSeason> {

    private GoalieSeasonRepository goalieSeasonRepository;

    @Autowired
    public GoalieSeasonService(GoalieSeasonRepository goalieSeasonRepository) {
        this.goalieSeasonRepository = goalieSeasonRepository;
    }


    //  METHODS

    @Override
    public void refresh(GoalieSeason entity) {
        this.goalieSeasonRepository.refresh(entity);
    }

    @Override
    public Optional<GoalieSeason> find(Long id) {
        return this.goalieSeasonRepository.findById(id);
    }

    @Override
    public List<GoalieSeason> findAll() {
        return Lists.newArrayList(this.goalieSeasonRepository.findAll());
    }

    @Override
    public GoalieSeason save(GoalieSeason entity) {
        return this.goalieSeasonRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

        if (find(id).isPresent()) {
            this.goalieSeasonRepository.deleteById(id);
        }
    }

    @Override
    public GoalieSeason create(Map<String, Object> params) {
        return null;
    }
}
