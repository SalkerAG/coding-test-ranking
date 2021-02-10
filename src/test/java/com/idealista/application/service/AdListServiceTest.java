package com.idealista.application.service;

import com.idealista.TestUtil;
import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.repository.AdListRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdListServiceTest {

    @Mock
    private AdListRepository adListRepository;

    @Autowired
    private AdListService adListService;

    @Test
    public void whenEmptyAdVOList_shouldNotReturnAds() {
        List<AdVO> ads = Collections.emptyList();
        when(adListRepository.listAds()).thenReturn(ads);
        assertNotEquals(ads.size(), adListService.listAds().size());
    }

    @Test
    public void whenNonEmptyAdVOList_shouldReturnAds(){
        List<AdVO> ads = TestUtil.fakeAdVOList();
        when(adListRepository.listAds()).thenReturn(ads);
        assertNotEquals(ads.size(), adListService.listAds().size());
    }


}
