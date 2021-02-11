package com.idealista.application.service;

import com.idealista.domain.model.entity.AdVO;

import java.util.List;

public interface AdListService {
    List<AdVO> listAds();
    List<AdVO> listQualityAds();
    void scoreAds();
}
