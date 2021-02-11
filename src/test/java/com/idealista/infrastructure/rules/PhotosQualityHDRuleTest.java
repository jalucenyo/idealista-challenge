package com.idealista.infrastructure.rules;

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
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PhotosQualityHDRuleTest {

    PhotosQualityHDRule rule;

    @MockBean
    InMemoryPersistence persistence;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        rule = new PhotosQualityHDRule(persistence);
    }

    @Test
    public void twoPhotosOneHDOtherSD() {

        AdVO adVO = new AdVO(5, "FLAT", "Pisazo,",
                Arrays.asList(1, 2), 300, null, null, null);
        when(persistence.getPicturesByAdId(anyInt())).thenReturn(Arrays.asList(
            new PictureVO(1, "http://www.idealista.com/pictures/2", "HD"),
            new PictureVO(2, "http://www.idealista.com/pictures/3", "SD")));

        int result = rule.compute(adVO);

        Assert.assertEquals(20, result);
    }

    @Test
    public void twoPhotosHD() {

        AdVO adVO = new AdVO(5, "FLAT", "Pisazo,",
                Arrays.asList(1, 2), 300, null, null, null);
        when(persistence.getPicturesByAdId(anyInt())).thenReturn(Arrays.asList(
                new PictureVO(1, "http://www.idealista.com/pictures/2", "HD"),
                new PictureVO(2, "http://www.idealista.com/pictures/3", "HD")));

        int result = rule.compute(adVO);

        Assert.assertEquals(40, result);
    }

    @Test
    public void towPhotosSD() {

        AdVO adVO = new AdVO(5, "FLAT", "Pisazo,",
                Arrays.asList(1, 2), 300, null, null, null);
        when(persistence.getPicturesByAdId(anyInt())).thenReturn(Arrays.asList(
                new PictureVO(1, "http://www.idealista.com/pictures/2", "SD"),
                new PictureVO(2, "http://www.idealista.com/pictures/3", "SD")));

        int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

    @Test
    public void emptyListPhotos() {

        AdVO adVO = new AdVO(5, "FLAT", "Pisazo,",
                Arrays.asList(1, 2), 300, null, null, null);
        when(persistence.getPicturesByAdId(anyInt())).thenReturn(Collections.emptyList());

        int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

}
