package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.idealista.infrastructure.rules.RulesConstants.CHALET;

@Slf4j
@Component
public class DescriptionBetween20of49ByChaletRule implements QualityRule {

    @Override
    public int compute(AdVO adVO) {
        int points = 0;
        if(adVO.getTypology().equals(CHALET)){
            int words = countWords(adVO);
            points = (words >= 20 && words <= 49)? getPoints() : 0;
        }
        log.info("Quality rule : Ad id={} rule={}, points={} ",  adVO.getId(), this.getClass().getSimpleName(), points);
        return points;
    }

    @Override
    public int getPoints() {
        return 10;
    }

    private int countWords(AdVO adVO) {
        return adVO.getDescription().split("\\s+").length;
    }

}
