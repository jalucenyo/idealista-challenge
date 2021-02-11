package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DescriptionContainsRule implements QualityRule {

    @Override
    public int compute(AdVO adVO) {
        int points = (int) Arrays.stream(adVO.getDescription().split(" "))
                .filter(wordDesc -> getContainsWords().stream()
                        .anyMatch(wordDesc::equalsIgnoreCase))
                .count() * getPoints();

        log.info("Quality rule : Ad id={} rule={}, points={} ",  adVO.getId(), this.getClass().getSimpleName(), points);
        return points;
    }

    @Override
    public int getPoints() {
        return 5;
    }

    public List<String> getContainsWords(){
        return Arrays.asList("Luminoso", "Nuevo", "Céntrico", "Centrico", "Reformado", "Ático", "Atico");
    }

}
