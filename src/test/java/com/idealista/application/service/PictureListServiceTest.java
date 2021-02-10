package com.idealista.application.service;

import com.idealista.TestUtil;
import com.idealista.domain.model.entity.PictureVO;
import com.idealista.domain.repository.PictureListRepository;
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
public class PictureListServiceTest {
    @Mock
    private PictureListRepository pictureListRepository;

    @Autowired
    private PictureListService pictureListService;

    @Test
    public void whenEmptyPictureVOList_shouldNotReturnPictures() {
        List<PictureVO> pictures = Collections.emptyList();
        when(pictureListRepository.listPictures()).thenReturn(pictures);
        assertNotEquals(pictures.size(), pictureListService.listPictures().size());
    }

    @Test
    public void whenNonEmptyPictureVOList_shouldReturnPictures(){
        List<PictureVO> pictures = TestUtil.fakePictureVOList();
        when(pictureListRepository.listPictures()).thenReturn(pictures);
        assertNotEquals(pictures.size(), pictureListService.listPictures().size());
    }

}
