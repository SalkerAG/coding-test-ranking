package com.idealista.infrastructure;

import com.idealista.TestUtil;
import com.idealista.application.service.AdListService;
import com.idealista.domain.mapper.AdMapper;
import com.idealista.domain.model.entity.AdVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @MockBean
    private AdMapper mapper;

    @Test
    public void whenAds_shouldReturnNoPublicAds() throws Exception {
        List<AdVO> ads = Collections.emptyList();

        when(adListService.listAds()).thenReturn(ads);
        mock.perform(get("/api/v1/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(0)));
    }

    @Test
    public void whenAds_shouldReturnPublicAds() throws Exception {
        List<AdVO> ads = Arrays.asList((TestUtil.fakeAdVO(1, "FLAT", "Test", Collections.emptyList(), null, null, null, null)));

        when(adListService.listAds().stream().map(mapper::adVOToPublicAd).collect(Collectors.toList())).thenReturn(ads.stream().map(mapper::adVOToPublicAd).collect(Collectors.toList()));
        mock.perform(get("/api/v1/ads"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(1)));
    }
}
