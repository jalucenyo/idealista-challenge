package com.idealista.infrastructure.services;


import com.idealista.infrastructure.persistence.models.AdVO;

import java.util.Date;

public interface QualityCalculatorService {

    void updateAllScoreAndIrrelevantSinceDate();

    int calculateScore(AdVO adVO);

    Date calculateIrrelevantSinceDate(AdVO adVO, int score);
}
