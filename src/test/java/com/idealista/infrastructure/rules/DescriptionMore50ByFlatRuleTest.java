package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
public class DescriptionMore50ByFlatRuleTest {

    DescriptionMore50ByFlatRule rule;

    @Before
    public void setUp() {
        rule = new DescriptionMore50ByFlatRule();
    }

    @Test
    public void descriptionMore50words() {

        AdVO adVO = new AdVO(1, "FLAT",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec feugiat lacus turpis, vitae egestas nisl fermentum at. Proin pellentesque pellentesque lacinia. Maecenas ornare magna eu faucibus dictum. Donec varius at mi sit amet vestibulum. Nulla ut turpis nunc. Curabitur dictum diam et dolor varius lacinia. Etiam eleifend sit amet risus.",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(30, result);
    }

    @Test
    public void descriptionLess50words() {

        AdVO adVO = new AdVO(1, "FLAT",
                "Lorem ipsum dolor amet, consectetur adipiscing elit. Donec feugiat lacus turpis, vitae egestas nisl fermentum at. Proin pellentesque pellentesque lacinia. Maecenas ornare magna eu faucibus dictum. Donec varius at mi sit amet vestibulum. Nulla ut turpis nunc. Curabitur dictum diam et dolor varius lacinia. Etiam eleifend sit amet risus.",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

    @Test
    public void applyRuleOnlyTypeChalet() {

        AdVO adVO = new AdVO(1, "CHALET",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec feugiat lacus turpis, vitae egestas nisl fermentum at. Proin pellentesque pellentesque lacinia. Maecenas ornare magna eu faucibus dictum. Donec varius at mi sit amet vestibulum. Nulla ut turpis nunc. Curabitur dictum diam et dolor varius lacinia. Etiam eleifend sit amet risus.",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

}
