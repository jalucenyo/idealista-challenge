package com.idealista.infrastructure.services;

import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.models.AdVO;
import com.idealista.infrastructure.rules.QualityRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class QualityCalculatorServiceImplTest {

    @MockBean
    InMemoryPersistence persistence;
    QualityCalculatorServiceImpl qualityCalculatorService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        qualityCalculatorService = new QualityCalculatorServiceImpl(getTestingRules(), persistence);
    }

    @Test
    public void calculateScore(){

        AdVO ad = new AdVO(6, "GARAGE", "", Arrays.asList(6), 300, null, null, null);
        int result = qualityCalculatorService.calculateScore(ad);
        Assert.assertEquals(10, result);

    }

    private List<QualityRule> getTestingRules(){
        return Arrays.asList(
            new QualityRule() {
                @Override public int compute(AdVO adVO) { return getPoints(); }
                @Override public int getPoints() { return 20;}},
            new QualityRule() {
                @Override public int compute(AdVO adVO) { return getPoints();}
                @Override public int getPoints() { return -10; }
        });
    }

}
