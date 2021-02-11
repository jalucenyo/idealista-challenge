package com.idealista.infrastructure.services;

import com.idealista.infrastructure.persistence.models.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.rules.QualityRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Component
public class QualityCalculatorServiceImpl implements QualityCalculatorService {

    final List<QualityRule> qualityRules;
    final InMemoryPersistence persistence;

    public QualityCalculatorServiceImpl(List<QualityRule> qualityRules, InMemoryPersistence persistence) {
        this.qualityRules = qualityRules;
        this.persistence = persistence;
    }

    @Override
    public void updateAllScoreAndIrrelevantSinceDate(){
        this.persistence.findAll().forEach(adVO -> {
            int score = calculateScore(adVO);
            Date irrelevantSince = calculateIrrelevantSinceDate(adVO, score);
            this.persistence.saveScoreAndIrrelevantSince(adVO.getId(), score, irrelevantSince);
        });
    }

    @Override
    public int calculateScore(final AdVO adVO) {
        int score = qualityRules.stream()
                .mapToInt(rule -> rule.compute(adVO))
                .sum();
        score = Math.max(0, Math.min(100, score));
        log.info("Quality rule : Ad id={} total score={} ",  adVO.getId(), score);
        return score;
    }

    @Override
    public Date calculateIrrelevantSinceDate(final AdVO adVO, final int score) {
        Date irrelevantSince = null;
        if(score < 40) {
            irrelevantSince = isNull(adVO.getIrrelevantSince())? new Date() : adVO.getIrrelevantSince();
        }
        return irrelevantSince;
    }

}
