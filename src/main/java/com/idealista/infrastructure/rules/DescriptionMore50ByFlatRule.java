package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.idealista.infrastructure.rules.RulesConstants.FLAT;

@Slf4j
@Component
public class DescriptionMore50ByFlatRule implements QualityRule {

    @Override
    public int compute(AdVO adVO) {
        int points = (adVO.getTypology().equals(FLAT) && countWords(adVO) >= 50)? getPoints() : 0;
        log.info("Quality rule : Ad id={} rule={}, points={} ",  adVO.getId(), this.getClass().getSimpleName(), points);
        return points;
    }

    @Override
    public int getPoints() {
        return 30;
    }

    private int countWords(AdVO adVO) {
        return adVO.getDescription().split("\\s+").length;
    }

}
