package com.idealista.infrastructure;

import com.idealista.application.service.AdListService;
import com.idealista.domain.mapper.AdMapper;
import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.model.dto.QualityAd;
import com.idealista.domain.model.entity.AdVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdsControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private AdListService adListService;

    @Autowired
    private AdMapper mapper;

    @Test
    public void whenAds_shouldReturnNoPublicAds() throws Exception {
        List<AdVO> ads = Collections.emptyList();
        List<PublicAd> publicAds = ads.stream().map(mapper::adVOToPublicAd).collect(Collectors.toList());
        when(adListService.listAds()).thenReturn(ads);
        mock.perform(get("/api/v1/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(0)));
    }

    @Test
    public void whenAdsButNoScore_shouldReturnNoPublicAds() throws Exception {
        List<AdVO> ads = new ArrayList<AdVO>();
        ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Collections.<Integer>emptyList(), 300, null, null, null));
        when(adListService.listAds()).thenReturn(ads);
        mock.perform(get("/api/v1/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(0)));
    }

    @Test
    public void whenAdsHaveScore_shouldReturnPublicAds() throws Exception {
        List<AdVO> ads = new ArrayList<AdVO>();
        ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Collections.<Integer>emptyList(), 300, null, 60, null));
        ads.add(new AdVO(2, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la " +
                "oportunidad y adquiera este ático de lujo", Arrays.asList(4), 300, null, null, null));
        List<PublicAd> publicAds = ads.stream().map(mapper::adVOToPublicAd).collect(Collectors.toList());
        when(adListService.listAds()).thenReturn(ads);
        mock.perform(get("/api/v1/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void whenAds_shouldReturnNoQualityAds() throws Exception {
        List<AdVO> ads = Collections.emptyList();
        List<QualityAd> publicAds = ads.stream().map(mapper::adVOToQualityAd).collect(Collectors.toList());
        when(adListService.listAds()).thenReturn(ads);
        mock.perform(get("/api/v1/quality-ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(0)));
    }

    @Test
    public void whenAdsButNoScore_shouldQualityAds() throws Exception {
        List<AdVO> ads = new ArrayList<AdVO>();
        ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Collections.<Integer>emptyList(), 300, null, null, null));
        List<QualityAd> publicAds = ads.stream().map(mapper::adVOToQualityAd).collect(Collectors.toList());
        when(adListService.listAds()).thenReturn(ads);
        mock.perform(get("/api/v1/quality-ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(1)));
    }
    @Test
    public void whenAdsHaveScore_shouldReturnQualityAds() throws Exception {
        List<AdVO> ads = new ArrayList<AdVO>();
        ads.add(new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                Collections.<Integer>emptyList(), 300, null, 30, null));
        ads.add(new AdVO(2, "FLAT", "Nuevo ático céntrico recién reformado. No deje pasar la " +
                "oportunidad y adquiera este ático de lujo", Arrays.asList(4), 300, null, 60, null));
        List<QualityAd> publicAds = ads.stream().map(mapper::adVOToQualityAd).collect(Collectors.toList());
        when(adListService.listAds()).thenReturn(ads);
        mock.perform(get("/api/v1/quality-ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void whenScoreCalculate_shouldRespondVoid() throws Exception {
        mock.perform(put("/api/v1/score-ads")).andExpect(status().isOk());
    }
}
