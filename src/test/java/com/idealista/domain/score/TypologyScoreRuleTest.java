package com.idealista.domain.score;

import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.score.rule.TypologyScoreRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypologyScoreRuleTest {

    @Autowired
    private TypologyScoreRule typologyScoreRule;

    @Test
    public void whenADVoTypeFlatDontApplyCriteria_shouldReturn0ToScore() throws Exception {
        AdVO ad = new AdVO(1, "FLAT", "Testing", Arrays.asList(1), null, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeFlatApplyCriteria_shouldReturn40ToScore() throws Exception {
        AdVO ad = new AdVO(1, "FLAT", "Testing", Arrays.asList(1), 200, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(40, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeChaletDontApplyCriteria_shouldReturn0ToScore() throws Exception {
        AdVO ad = new AdVO(1, "CHALET", "Testing", Arrays.asList(1), 200, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeChaletApplyCriteria_shouldReturn40ToScore() throws Exception {
        AdVO ad = new AdVO(1, "CHALET", "Testing", Arrays.asList(1), 200, 20, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(40, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeGarageDontApplyCriteria_shouldReturn0ToScore() throws Exception {
        AdVO ad = new AdVO(1, "GARAGE", "Testing", Collections.emptyList(), null, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeGarageApplyCriteria_shouldReturn40ToScore() throws Exception {
        AdVO ad = new AdVO(1, "GARAGE", "", Arrays.asList(1), null, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(40, (int) ad.getScore());
    }

    @Test
    public void whenADVoDifferentType_shouldReturn0ToScore() throws Exception {
        AdVO ad = new AdVO(1, "", "Testing", Arrays.asList(1), 200, 20, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }
}