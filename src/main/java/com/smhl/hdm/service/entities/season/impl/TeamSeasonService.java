package com.smhl.hdm.service.entities.season.impl;

import com.google.common.collect.Lists;
import com.smhl.hdm.models.entities.season.impl.TeamSeason;
import com.smhl.hdm.repositories.season.TeamSeasonRepository;
import com.smhl.hdm.service.entities.season.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the season service for teams
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class TeamSeasonService implements SeasonService<TeamSeason> {

    private TeamSeasonRepository teamSeasonRepository;

    @Autowired
    public TeamSeasonService(TeamSeasonRepository teamSeasonRepository) {
        this.teamSeasonRepository = teamSeasonRepository;
    }

    @Override
    public void refresh(TeamSeason entity) {
        this.teamSeasonRepository.refresh(entity);
    }

    @Override
    public Optional<TeamSeason> find(Long id) {
        return this.teamSeasonRepository.findById(id);
    }

    @Override
    public List<TeamSeason> findAll() {
        return Lists.newArrayList(this.teamSeasonRepository.findAll());
    }

    @Override
    public TeamSeason save(TeamSeason entity) {
        return this.teamSeasonRepository.save(entity);
    }

    @Override
    public void delete(Long id) {

        if (find(id).isPresent()) {
            this.teamSeasonRepository.deleteById(id);
        }
    }

    @Override
    public TeamSeason create(Map<String, Object> params) {
        return null;
    }
}
