package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class DescriptionContainsRuleTest {

    DescriptionContainsRule rule;

    @Before
    public void setUp(){
        rule = new DescriptionContainsRule();
    }

    @Test
    public void typeFlatAccomplishRule(){

        AdVO adVO = new AdVO(4,"FLAT",
                "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                Arrays.asList(5), 300, null, null, null);

        int result = rule.compute(adVO);
        Assert.assertEquals(20, result);
    }

    @Test
    public void typeFlatAccomplishRule2(){

        AdVO adVO = new AdVO(4,"FLAT",
                "Ático céntrico muy LUMINOSO y recién reformado, parece nuevo",
                Arrays.asList(5), 300, null, null, null);

        int result = rule.compute(adVO);
        Assert.assertEquals(20, result);
    }

}
