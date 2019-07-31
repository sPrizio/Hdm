package com.smhl.hdm.scheduledtasks;

import com.smhl.hdm.models.entities.seasonstring.SeasonString;
import com.smhl.hdm.service.entities.seasonstring.SeasonStringService;
import com.smhl.hdm.utils.HdmUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A chronic "job" that runs on the first day of every month to check whether a new season needs to be created
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class StartNewSeasonTask {

    private SeasonStringService seasonStringService;

    @Autowired
    public StartNewSeasonTask(SeasonStringService seasonStringService) {
        this.seasonStringService = seasonStringService;
    }

    @Scheduled(cron = "0 0 14 2 * ? *", zone = "GMT-4:00")
    public void startNewSeason() {

        List<SeasonString> seasonStrings = this.seasonStringService.getAllSeasonStrings();

        boolean doesNotHaveSeasonString =
                seasonStrings
                .stream()
                .noneMatch(seasonString -> seasonString.getSeason().equalsIgnoreCase(HdmUtils.getCurrentSeasonString()));

        if (doesNotHaveSeasonString) {
            this.seasonStringService.save(new SeasonString(HdmUtils.getCurrentSeasonString()));
        }
    }
}
