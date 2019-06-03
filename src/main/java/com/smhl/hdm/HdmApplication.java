package com.smhl.hdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HdmApplication {

    //  TODO: implement logic to handle players/goalies with less than 2 games played. We don't want to consider them for the stats leaders

    //  TODO: add league averages for /stats

    public static void main(String[] args) {
        SpringApplication.run(HdmApplication.class, args);
    }

}
