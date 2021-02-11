package com.idealista.domain.score;

import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.score.rule.DescriptionSpecialsWordsScoreRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptionSpecialsWordsScoreRuleTest {

    @Autowired
    private DescriptionSpecialsWordsScoreRule descriptionSpecialsWordsScoreRule;

    @Test
    public void whenADVoHasNoDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = new AdVO(1, "", "", Collections.emptyList(), null, null, null, null);
        descriptionSpecialsWordsScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoHas2SpecialWords_shouldReturn10ToScore() throws Exception {
        AdVO ad = new AdVO(1, "", "luminoso nuevo", Collections.emptyList(), null, null, null, null);
        descriptionSpecialsWordsScoreRule.execute(ad);
        assertEquals(10, (int) ad.getScore());
    }

    @Test
    public void whenADVoHas5SpecialWords_shouldReturn25ToScore() throws Exception {
        AdVO ad = new AdVO(1, "", "luminoso nuevo céntrico reformado ático", Collections.emptyList(), null, null, null, null);
        descriptionSpecialsWordsScoreRule.execute(ad);
        assertEquals(25, (int) ad.getScore());
    }

    @Test
    public void whenADVoHas5SpecialWordsNotNormalize_shouldReturn25ToScore() throws Exception {
        AdVO ad = new AdVO(1, "", "LuMinoso nuevO centríco reformado Ático", Collections.emptyList(), null, null, null, null);
        descriptionSpecialsWordsScoreRule.execute(ad);
        assertEquals(25, (int) ad.getScore());
    }
}

