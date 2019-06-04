package com.smhl.hdm.repositories;

import com.smhl.hdm.models.entities.HdmEntity;

/**
 * Parent-level repository designed for custom, yet common functionality
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">http://www.saprizio.com</a>
 * @version 1.0
 */
public interface HdmRepository {

    void refresh(HdmEntity hdmEntity);
}
