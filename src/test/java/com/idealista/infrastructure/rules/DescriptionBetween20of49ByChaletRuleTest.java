package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
public class DescriptionBetween20of49ByChaletRuleTest {

    DescriptionBetween20of49ByChaletRule rule;

    @Before
    public void setUp() {
        rule = new DescriptionBetween20of49ByChaletRule();
    }

    @Test
    public void descriptionMore49words() {

       AdVO adVO = new AdVO(1, "CHALET",
               "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec feugiat lacus turpis, vitae egestas nisl fermentum at. Proin pellentesque pellentesque lacinia. Maecenas ornare magna eu faucibus dictum. Donec varius at mi sit amet vestibulum. Nulla ut turpis nunc. Curabitur dictum diam et dolor varius lacinia. Etiam eleifend sit amet risus.",
               Collections.<Integer>emptyList(), 300, null, null, null);

       int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

    @Test
    public void descriptionBetweenOf20_49words() {

        AdVO adVO = new AdVO(1, "CHALET",
                "Lorem ipsum dolor amet, consectetur adipiscing elit. Donec feugiat lacus turpis, vitae egestas nisl fermentum at. Proin pellentesque pellentesque lacinia. Maecenas ornare magna eu faucibus dictum. Donec varius at mi sit amet vestibulum. Nulla ut turpis nunc. Curabitur dictum diam et dolor varius lacinia. Etiam eleifend sit amet risus.",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(10, result);
    }

    @Test
    public void descriptionLess20words() {

        AdVO adVO = new AdVO(1, "CHALET",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut a scelerisque nunc, non ornare sapien. Sed at erat ut.",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

    @Test
    public void applyRuleOnlyTypeChalet() {

        AdVO adVO = new AdVO(1, "FLAT",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut a scelerisque nunc, non ornare sapien. Sed at erat ut.",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

}
