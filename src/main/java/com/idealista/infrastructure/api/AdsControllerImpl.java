package com.idealista.infrastructure.api;

import java.util.List;

import com.idealista.infrastructure.api.dtos.PublicAd;
import com.idealista.infrastructure.api.dtos.QualityAd;
import com.idealista.infrastructure.services.AdsService;
import com.idealista.infrastructure.services.QualityCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ads")
public class AdsControllerImpl implements AdsController{

    private final QualityCalculatorService qualityCalculatorService;
    private final AdsService adsService;

    public AdsControllerImpl(QualityCalculatorService qualityCalculatorService, AdsService adsService) {
        this.qualityCalculatorService = qualityCalculatorService;
        this.adsService = adsService;
    }

    @GetMapping(path = "/quality")
    @Override
    public ResponseEntity<List<QualityAd>> qualityListing() {
        return ResponseEntity.ok(adsService.getAdsIrrelevant());
    }

    @GetMapping(path = "/public")
    @Override
    public ResponseEntity<List<PublicAd>> publicListing() {
        return ResponseEntity.ok(adsService.getAdsPublicList());
    }

    @PostMapping(path = "/calculate_score")
    @Override
    public ResponseEntity<Void> calculateScore() {
        qualityCalculatorService.updateAllScoreAndIrrelevantSinceDate();
        return ResponseEntity.ok().build();
    }

}
