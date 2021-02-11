package com.idealista.infrastructure.rules;

import com.idealista.infrastructure.persistence.models.AdVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

@RunWith(SpringRunner.class)
public class WithoutPhotoRuleTest {


    WithoutPhotoRule rule;

    @Before
    public void setUp() {
        rule = new WithoutPhotoRule();
    }

    @Test
    public void withPhotos() {
        AdVO adVO = new AdVO(1, "FLAT",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                Arrays.asList(3, 8), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(0, result);
    }

    @Test
    public void withoutPhotos() {
        AdVO adVO = new AdVO(1, "FLAT",
                "",
                Collections.<Integer>emptyList(), 300, null, null, null);

        int result = rule.compute(adVO);

        Assert.assertEquals(-10, result);
    }


}
