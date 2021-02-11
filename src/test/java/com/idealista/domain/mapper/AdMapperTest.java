package com.idealista.domain.mapper;

import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.model.dto.QualityAd;
import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.model.entity.PictureVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdMapperTest {
    @Autowired
    private AdMapper mapper;

    @Test
    public void whenGetAdVO_shouldReturnPublicAd() throws Exception {
        AdVO ad = new AdVO(1, "FLAT", "", Arrays.asList(1), null, null, null, null);
        PictureVO pic = new PictureVO(1, "test.test", "HD");

        PublicAd pubAd = mapper.adVOToPublicAd(ad);
        pubAd.setPictureUrls(Arrays.asList(pic.getUrl()));

        assertEquals(PublicAd.class, mapper.adVOToPublicAd(ad).getClass());
        assertEquals(ad.getId(), pubAd.getId());
        assertEquals(ad.getTypology(), pubAd.getTypology());
        assertEquals(ad.getDescription(), pubAd.getDescription());
        assertEquals(Arrays.asList(pic.getUrl()), pubAd.getPictureUrls());
        assertEquals(ad.getHouseSize(), pubAd.getHouseSize());
        assertEquals(ad.getGardenSize(), pubAd.getGardenSize());
    }

    @Test
    public void whenGetAdVO_shouldReturnQualityAd() throws Exception {
        AdVO ad = new AdVO(1, "FLAT", "", Arrays.asList(1), null, null, null, null);
        PictureVO pic = new PictureVO(1, "test.test", "HD");

        QualityAd quaAd = mapper.adVOToQualityAd(ad);
        quaAd.setPictureUrls(Arrays.asList(pic.getUrl()));

        assertEquals(QualityAd.class, mapper.adVOToQualityAd(ad).getClass());
        assertEquals(ad.getId(), quaAd.getId());
        assertEquals(ad.getTypology(), quaAd.getTypology());
        assertEquals(ad.getDescription(), quaAd.getDescription());
        assertEquals(Arrays.asList(pic.getUrl()), quaAd.getPictureUrls());
        assertEquals(ad.getHouseSize(), quaAd.getHouseSize());
        assertEquals(ad.getGardenSize(), quaAd.getGardenSize());
        assertEquals(ad.getScore(), quaAd.getScore());
        assertEquals(ad.getIrrelevantSince(), quaAd.getIrrelevantSince());
    }
}
