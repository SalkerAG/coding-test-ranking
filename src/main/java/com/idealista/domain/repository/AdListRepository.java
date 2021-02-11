package com.idealista.domain.repository;

import com.idealista.domain.model.entity.AdVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdListRepository {
    List<AdVO> listAds();
}
