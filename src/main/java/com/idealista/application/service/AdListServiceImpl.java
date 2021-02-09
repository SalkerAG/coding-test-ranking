package com.idealista.application.service;

import com.idealista.domain.mapper.AdMapper;
import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.repository.AdListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdListServiceImpl implements AdListService {

    private AdListRepository adListRepository;
    private AdMapper adMapper;

    public AdListServiceImpl(AdListRepository adListRepository, AdMapper adMapper){
        this.adListRepository = adListRepository;
        this.adMapper = adMapper;
    }

    @Override
    public List<PublicAd> listAds() {
        return adListRepository.listAds().stream().map(ad -> adMapper.adVOToPublicAd(ad)).collect(Collectors.toList());
    }
}
