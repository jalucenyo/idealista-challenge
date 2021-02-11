package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;

public interface QualityRule {

    int compute(AdVO adVO);

    int getPoints();

}
