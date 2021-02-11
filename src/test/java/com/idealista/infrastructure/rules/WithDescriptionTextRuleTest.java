package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
public class WithDescriptionTextRuleTest {

    WithDescriptionTextRule rule;

    @Before
    public void setUp() {
        rule = new WithDescriptionTextRule();
    }

    @Test
    public void withDescription() {
        AdVO adVO = new AdVO(1, "FLAT",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec feugiat lacus turpis, vitae egestas nisl fermentum at. Proin pellentesque pellentesque lacinia. Maecenas ornare magna eu faucibus dictum. Donec varius at mi sit amet vestibulum. Nulla ut turpis nunc. Curabitur dictum diam et dolor varius lacinia. Etiam eleifend sit amet risus.",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(5, result);
    }

    @Test
    public void withoutDescription() {
        AdVO adVO = new AdVO(1, "FLAT",
                "",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

}
