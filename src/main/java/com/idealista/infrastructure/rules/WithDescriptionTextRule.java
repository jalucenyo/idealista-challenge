package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class WithDescriptionTextRule implements QualityRule {

    @Override
    public int compute(AdVO adVO) {
        int points = StringUtils.isEmpty(adVO.getDescription()) ? 0 : getPoints();
        log.info("Quality rule : Ad id={} rule={}, points={} ",  adVO.getId(), this.getClass().getSimpleName(), points);
        return points;
    }

    @Override
    public int getPoints() {
        return 5;
    }
}
