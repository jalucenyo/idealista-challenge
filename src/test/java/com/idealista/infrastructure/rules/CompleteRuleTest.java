package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
public class CompleteRuleTest {

    CompleteRule completeRule;

    @Before
    public void setUp(){
        completeRule = new CompleteRule();
    }

    @Test
    public void typeFlatAccomplishRule(){

        AdVO adVO = new AdVO(4,"FLAT",
                "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                Arrays.asList(5), 300, null, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(40, result);
    }

    @Test
    public void typeFlatNotAccomplishRuleHouseSizeIsZero(){

        AdVO adVO = new AdVO(4,"FLAT",
                "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                Arrays.asList(5), 0, null, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(0, result);
    }

    @Test
    public void typeFlatNotAccomplishRuleHouseSizeIsNull(){

        AdVO adVO = new AdVO(4,"FLAT",
                "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                Arrays.asList(5), null, null, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(0, result);
    }

    @Test
    public void typeFlatNotAccomplishRuleWithoutDescription(){

        AdVO adVO = new AdVO(4,"FLAT",
                null,
                Arrays.asList(5), 10, null, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(0, result);
    }

    @Test
    public void typeChaletsAccomplishRule(){

        AdVO adVO = new AdVO(4,"CHALET",
                "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                Arrays.asList(5), 100, 200, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(40, result);
    }

    @Test
    public void typeChaletsNotAccomplishRuleGardenSizeIsZero(){

        AdVO adVO = new AdVO(4,"CHALET",
                "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                Arrays.asList(5), 100, 0, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(0, result);
    }

    @Test
    public void typeChaletsNotAccomplishRuleGardenSizeIsNull(){

        AdVO adVO = new AdVO(4,"CHALET",
                "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                Arrays.asList(5), 100, null, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(0, result);
    }


    @Test
    public void notAccomplishRuleWithoutPhotos(){

        AdVO adVO = new AdVO(4,"CHALET",
                "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                new ArrayList<>(), 100, null, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(0, result);
    }


    @Test
    public void typeGarageAccomplishRuleWithoutDescription(){

        AdVO adVO = new AdVO(4,"GARAGE",null,
                Arrays.asList(5), null, null, null, null);

        int result = completeRule.compute(adVO);
        Assert.assertEquals(40, result);
    }


}
