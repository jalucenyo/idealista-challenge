package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.models.PictureVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.idealista.infrastructure.rules.RulesConstants.PHOTO_SD;

@Slf4j
@Component
public class PhotosQualitySDRule implements QualityRule {

    final InMemoryPersistence persistence;

    public PhotosQualitySDRule(InMemoryPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public int compute(AdVO adVO) {
        List<PictureVO> pictures = this.persistence.getPicturesByAdId(adVO.getId());
        int points = (int) pictures.stream()
                .filter(pic -> pic.getQuality().equals(PHOTO_SD))
                .count() * getPoints();

        log.info("Quality rule : Ad id={} rule={}, points={} ",  adVO.getId(), this.getClass().getSimpleName(), points);
        return points;
    }

    @Override
    public int getPoints() {
        return 10;
    }
}
