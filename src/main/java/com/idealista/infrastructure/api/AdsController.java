package com.idealista.infrastructure.api;

import com.idealista.infrastructure.api.dtos.PublicAd;
import com.idealista.infrastructure.api.dtos.QualityAd;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(value = "Ads ", tags = { "Ads" })
public interface AdsController {

    ResponseEntity<List<QualityAd>> qualityListing();

    ResponseEntity<List<PublicAd>> publicListing();

    ResponseEntity<Void> calculateScore();

}
