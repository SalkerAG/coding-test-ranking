package com.idealista.domain.mapper;


import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.model.dto.QualityAd;
import com.idealista.domain.model.entity.AdVO;

public interface AdMapper {

    PublicAd adVOToPublicAd(AdVO ad);
    QualityAd adVOToQualityAd(AdVO ad);

}
