package com.smhl.hdm.controllers.response;

import com.smhl.hdm.enums.HdmApiResponseResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A custom class to house the response from a typical API request to the hdm system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class HdmApiResponse {

    @Getter
    @Setter
    private HdmApiResponseResult response;

    @Getter
    @Setter
    private Object data;
}
