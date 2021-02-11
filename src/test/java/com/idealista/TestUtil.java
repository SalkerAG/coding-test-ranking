package com.idealista;

import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.model.entity.PictureVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtil {

    public static List<AdVO> fakeAdVOList() {
        List<AdVO> ads = new ArrayList<AdVO>();
        ads.add(new AdVO(1, "CHALET", "Bonita casa adosada, con piscina y amplia terraza.", Collections.singletonList(1), 200, 20, 80, null));
        return ads;
    }

    public static List<PictureVO> fakePictureVOList() {
        List<PictureVO> pictures = new ArrayList<PictureVO>();
        pictures.add(new PictureVO(1, "http://www.idealista.com/pictures/1", "SD"));
        return pictures;
    }
}
