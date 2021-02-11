package com.idealista.domain.score;

import com.idealista.TestUtil;
import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.score.rule.DescriptionLengthScoreRule;
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
    public void whenADVoTypeFlatHasNoDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "", "", Collections.emptyList(), null, null, null, null);
        descriptionScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeFlatHasDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "", "Test", Collections.emptyList(), null, null, null, null);
        descriptionScoreRule.execute(ad);
        assertEquals(5, (int) ad.getScore());
    }

}
