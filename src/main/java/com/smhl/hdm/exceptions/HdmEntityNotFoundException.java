package com.smhl.hdm.exceptions;

/**
 * An exception for entities that could not be found in the Hdm system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public class HdmEntityNotFoundException extends Exception {

    public HdmEntityNotFoundException(String message) {
        super(message);
    }
}
