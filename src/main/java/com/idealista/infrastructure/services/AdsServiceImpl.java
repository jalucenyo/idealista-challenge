package com.idealista.infrastructure.services;

import com.idealista.infrastructure.api.dtos.PublicAd;
import com.idealista.infrastructure.api.dtos.QualityAd;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.models.AdVO;
import com.idealista.infrastructure.persistence.models.PictureVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdsServiceImpl implements AdsService {

    private final InMemoryPersistence persistence;

    public AdsServiceImpl(InMemoryPersistence persistence) {
        this.persistence = persistence;
    }

    @Override
    public List<PublicAd> getAdsPublicList() {
        return persistence.findAllByScoreMoreOf40Points().stream()
                .map(this::mapAdVOToPublicAd)
                .collect(Collectors.toList());
    }

    @Override
    public List<QualityAd> getAdsIrrelevant() {
        return persistence.findAllByScoreLessOf40Points().stream()
                .map(this::mapAdVOToQualityAd)
                .collect(Collectors.toList());
    }


    private QualityAd mapAdVOToQualityAd(com.idealista.infrastructure.persistence.models.AdVO adVO) {
        return QualityAd.builder()
                .id(adVO.getId())
                .description(adVO.getDescription())
                .gardenSize(adVO.getGardenSize())
                .houseSize(adVO.getHouseSize())
                .pictureUrls(getPicturesUrlFromAdVO(adVO))
                .typology(adVO.getTypology())
                .score(adVO.getScore())
                .irrelevantSince(adVO.getIrrelevantSince())
                .build();
    }

    private PublicAd mapAdVOToPublicAd(com.idealista.infrastructure.persistence.models.AdVO adVO) {
        return PublicAd.builder()
                .id(adVO.getId())
                .description(adVO.getDescription())
                .typology(adVO.getTypology())
                .gardenSize(adVO.getGardenSize())
                .houseSize(adVO.getHouseSize())
                .pictureUrls(getPicturesUrlFromAdVO(adVO))
                .build();
    }

    private List<String> getPicturesUrlFromAdVO(AdVO adVO) {
        return persistence.getPicturesByAdId(adVO.getId()).stream()
                .map(PictureVO::getUrl).collect(Collectors.toList());
    }
}
