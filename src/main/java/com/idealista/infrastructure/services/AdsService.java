package com.idealista.infrastructure.services;

import com.idealista.infrastructure.api.dtos.PublicAd;
import com.idealista.infrastructure.api.dtos.QualityAd;

import java.util.List;

public interface AdsService {

    List<PublicAd> getAdsPublicList();

    List<QualityAd> getAdsIrrelevant();
}
