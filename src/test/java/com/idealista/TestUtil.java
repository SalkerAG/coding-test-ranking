package com.idealista;

import com.idealista.domain.model.entity.AdVO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtil {

    public static List<AdVO> fakeAdVOList() {
        List<AdVO> ads = new ArrayList<AdVO>();
        ads.add(new AdVO(1, "CHALET", "Bonita casa adosada, con piscina y amplia terraza.", Collections.singletonList(1), 200, 20, 80, null));
        return ads;
    }
}
