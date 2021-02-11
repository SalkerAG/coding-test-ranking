package com.idealista.domain.score;

import com.idealista.TestUtil;
import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.score.rule.PictureScoreRule;
import com.idealista.domain.score.rule.TypologyScoreRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypologyScoreRuleTest {

    @Autowired
    private TypologyScoreRule typologyScoreRule;

    @Test
    public void whenADVoTypeFlatDontApplyCriteria_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "FLAT", "Testing", List.of(1), null, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeFlatApplyCriteria_shouldReturn40ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "FLAT", "Testing", List.of(1), 200, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(40, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeChaletDontApplyCriteria_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "CHALET", "Testing", List.of(1), 200, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeChaletApplyCriteria_shouldReturn40ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "CHALET", "Testing", List.of(1), 200, 20, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(40, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeGarageDontApplyCriteria_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "GARAGE", "Testing", Collections.emptyList(), null, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeGarageApplyCriteria_shouldReturn40ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "GARAGE", "", List.of(1), null, null, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(40, (int) ad.getScore());
    }

    @Test
    public void whenADVoDifferentType_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "", "Testing", List.of(1), 200, 20, null, null);
        typologyScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }
}