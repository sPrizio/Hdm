package com.smhl.hdm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The primary application runner used by Spring to execute our application
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@SpringBootApplication
public class HdmApplication {

    /**
     * Runs the app. Not much else to say here
     *
     * @param args runtime arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(HdmApplication.class, args);
    }

}
