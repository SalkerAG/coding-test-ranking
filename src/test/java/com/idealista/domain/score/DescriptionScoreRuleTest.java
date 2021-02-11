package com.idealista.domain.score;

import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.score.rule.DescriptionScoreRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptionScoreRuleTest {

    @Autowired
    private DescriptionScoreRule descriptionScoreRule;

    @Test
    public void whenADVoHasNoDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = new AdVO(1, "", "", Collections.emptyList(), null, null, null, null);
        descriptionScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoHasDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = new AdVO(1, "", "Test", Collections.emptyList(), null, null, null, null);
        descriptionScoreRule.execute(ad);
        assertEquals(5, (int) ad.getScore());
    }

}
