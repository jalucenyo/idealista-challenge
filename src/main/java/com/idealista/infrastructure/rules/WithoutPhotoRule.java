package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WithoutPhotoRule implements QualityRule {

    @Override
    public int compute(AdVO adVO) {
        int points =  (adVO.getPictures() == null || adVO.getPictures().isEmpty()) ? getPoints() : 0;
        log.info("Quality rule : Ad id={} rule={}, points={} ",  adVO.getId(), this.getClass().getSimpleName(), points);
        return points;
    }

    @Override
    public int getPoints() {
        return -10;
    }
}
