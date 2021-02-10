package com.idealista.application.service;

import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.model.dto.QualityAd;

import java.util.List;

public interface AdListService {
    List<PublicAd> listAds();
    List<QualityAd> listQualityAds();
}
