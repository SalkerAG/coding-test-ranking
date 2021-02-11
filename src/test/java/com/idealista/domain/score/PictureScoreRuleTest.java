package com.idealista.domain.score;

import com.idealista.TestUtil;
import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.score.rule.PictureScoreRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PictureScoreRuleTest {

    @Autowired
    private PictureScoreRule pictureScoreRule;

    @Test
    public void whenADVoHasNoPicture_shouldReturnMinus10ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "", "", Collections.emptyList(), null, null, null, null);
        pictureScoreRule.execute(ad);
        assertEquals(-10, (int) ad.getScore());
    }

    @Test
    public void whenADVoHasOneHDPicture_shouldReturn20ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "", "", Arrays.asList(2), null, null, null, null);
        pictureScoreRule.execute(ad);
        assertEquals(20, (int) ad.getScore());
    }

    @Test
    public void whenADVoHasOnePictureNotHD_shouldReturn10ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "", "", Arrays.asList(1), null, null, null, null);
        pictureScoreRule.execute(ad);
        assertEquals(10, (int) ad.getScore());
    }

    @Test
    public void whenADVoHasMoreThanOnePicture_shouldReturnM30ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "", "",Arrays.asList(1,2), null, null, null, null);
        pictureScoreRule.execute(ad);
        assertEquals(30, (int) ad.getScore());
    }

}