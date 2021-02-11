package com.idealista.infrastructure.services;

import com.idealista.infrastructure.api.dtos.PublicAd;
import com.idealista.infrastructure.api.dtos.QualityAd;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.models.AdVO;
import com.idealista.infrastructure.persistence.models.PictureVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class AdsServiceImplTest {

    @MockBean
    InMemoryPersistence persistence;

    AdsServiceImpl adsService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        adsService = new AdsServiceImpl(persistence);
    }

    @Test
    public void mappingAdVOToQualityAd(){

        when(persistence.getPicturesByAdId(anyInt())).thenReturn(Arrays.asList(
                PictureVO.builder().id(1).url("Picture1").quality("HD").build(),
                PictureVO.builder().id(1).url("Picture2").quality("HD").build()
        ));

        when(persistence.findAllByScoreLessOf40Points()).thenReturn(Arrays.asList(
                AdVO.builder().id(1).description("Test1").gardenSize(10).houseSize(20).typology("TYP1").score(100).irrelevantSince(new Date()).pictures(Arrays.asList(1,2)).build(),
                AdVO.builder().id(2).description("Test2").gardenSize(20).houseSize(30).typology("TYP2").score(0).irrelevantSince(new Date()).pictures(Arrays.asList(1,2)).build()
        ));

        List<QualityAd> result = adsService.getAdsIrrelevant();

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(2, result.get(0).getPictureUrls().size());
        Assert.assertEquals(2, result.get(1).getPictureUrls().size());
        Assert.assertEquals("Test1", result.get(0).getDescription());
        Assert.assertEquals(Integer.valueOf(10), result.get(0).getGardenSize());
        Assert.assertEquals(Integer.valueOf(20), result.get(0).getHouseSize());
        Assert.assertEquals(Integer.valueOf(100), result.get(0).getScore());
        Assert.assertEquals("TYP1", result.get(0).getTypology());
        Assert.assertNotNull(result.get(0).getIrrelevantSince());

    }


    @Test
    public void mappingAdVOToPublicAd(){

        when(persistence.getPicturesByAdId(anyInt())).thenReturn(Arrays.asList(
                PictureVO.builder().id(1).url("Picture1").quality("HD").build(),
                PictureVO.builder().id(1).url("Picture2").quality("HD").build()
        ));

        when(persistence.findAllByScoreMoreOf40Points()).thenReturn(Arrays.asList(
                AdVO.builder().id(1).description("Test1").gardenSize(10).houseSize(20).typology("TYP1").pictures(Arrays.asList(1,2)).build(),
                AdVO.builder().id(2).description("Test2").gardenSize(20).houseSize(30).typology("TYP2").pictures(Arrays.asList(1,2)).build()
        ));

        List<PublicAd> result = adsService.getAdsPublicList();

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(2, result.get(0).getPictureUrls().size());
        Assert.assertEquals(2, result.get(1).getPictureUrls().size());
        Assert.assertEquals("Test1", result.get(0).getDescription());
        Assert.assertEquals(Integer.valueOf(10), result.get(0).getGardenSize());
        Assert.assertEquals(Integer.valueOf(20), result.get(0).getHouseSize());
        Assert.assertEquals("TYP1", result.get(0).getTypology());

    }


}
