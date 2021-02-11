package com.idealista.domain.score;

import com.idealista.TestUtil;
import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.score.rule.DescriptionLengthScoreRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptionLengthScoreRuleTest {

    @Autowired
    private DescriptionLengthScoreRule descriptionLengthScoreRule;

    private static final String MEDIUM_SIZE_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Suspendisse maximus odio non metus euismod rutrum. Duis tempus augue sed imperdiet interdum. " +
            "Pellentesque molestie elit vitae.";
    private static final String LONG_SIZE_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam id nunc ex. " +
            "Sed at eros sed nisl elementum iaculis. Mauris malesuada dictum gravida. Quisque suscipit dui ac dolor " +
            "consequat, a eleifend lacus fringilla. Etiam elementum, ipsum ut porttitor pharetra, nibh diam " +
            "accumsan lacus, ut ultricies risus mauris nec orci. Vivamus magna sem, auctor eget sapien.";

    @Test
    public void whenADVoTypeFlatHasNoDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "FLAT", "", Collections.emptyList(), null, null, null, null);
        descriptionLengthScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeFlatHasShortDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "FLAT", "Testing", Collections.emptyList(), null, null, null, null);
        descriptionLengthScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeFlatHasMediumDescription_shouldReturn10ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "FLAT", MEDIUM_SIZE_STRING, Collections.emptyList(), null, null, null, null);
        descriptionLengthScoreRule.execute(ad);
        assertEquals(10, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeFlatHasLongDescription_shouldReturn30ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "FLAT", LONG_SIZE_STRING, Collections.emptyList(), null, null, null, null);
        descriptionLengthScoreRule.execute(ad);
        assertEquals(30, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeChaletHasNoDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "CHALET", "", Collections.emptyList(), null, null, null, null);
        descriptionLengthScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeChatHasShortDescription_shouldReturn0ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "CHALET", "Testing", Collections.emptyList(), null, null, null, null);
        descriptionLengthScoreRule.execute(ad);
        assertEquals(0, (int) ad.getScore());
    }

    @Test
    public void whenADVoTypeChaletHasMediumDescription_shouldReturn10ToScore() throws Exception {
        AdVO ad = TestUtil.fakeAdVO(1, "CHALET", LONG_SIZE_STRING, Collections.emptyList(), null, null, null, null);
        descriptionLengthScoreRule.execute(ad);
        assertEquals(20, (int) ad.getScore());
    }

}
