package com.idealista.domain.repository;

import com.idealista.domain.model.entity.AdVO;

import java.util.List;

public interface AdListRepository {
    List<AdVO> listAds();
}
