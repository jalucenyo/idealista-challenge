package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.idealista.infrastructure.rules.RulesConstants.*;

@Slf4j
@Component
public class CompleteRule implements QualityRule {

    @Override
    public int compute(AdVO adVO) {
        int points = (withDescriptionExceptionGarage(adVO) &&
                withOneOrMorePhotos(adVO) &&
                isFlatWithHouseSize(adVO) &&
                isChaletWithHouseSizeAndGarden(adVO)
            )? getPoints() : 0;
        log.info("Quality rule : Ad id={} rule={}, points={} ",  adVO.getId(), this.getClass().getSimpleName(), points);
        return points;
    }

    @Override
    public int getPoints() {
        return 40;
    }

    private boolean withDescriptionExceptionGarage(AdVO adVO){
        if(adVO.getTypology().equals(GARAGE)) return true;
        return (adVO.getDescription() != null && adVO.getDescription().length() > 0);
    }

    private boolean withOneOrMorePhotos(AdVO adVO) {
        return (adVO.getPictures() != null && !adVO.getPictures().isEmpty());
    }

    private boolean isFlatWithHouseSize(AdVO adVO) {
        if (adVO.getTypology().equals(FLAT)) {
            return (adVO.getHouseSize() != null && adVO.getHouseSize() > 0);
        }
        return true;
    }

    private boolean isChaletWithHouseSizeAndGarden(AdVO adVO){
        if (adVO.getTypology().equals(CHALET)) {
            return (adVO.getHouseSize() != null
                    && adVO.getGardenSize() != null
                    && adVO.getHouseSize() > 0
                    && adVO.getGardenSize() > 0);
        }
        return true;
    }

}
