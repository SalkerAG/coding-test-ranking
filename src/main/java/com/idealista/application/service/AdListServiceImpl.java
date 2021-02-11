package com.idealista.application.service;

import com.idealista.domain.mapper.AdMapper;
import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.model.dto.QualityAd;
import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.repository.AdListRepository;
import com.idealista.domain.score.AdScore;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdListServiceImpl implements AdListService {

    private final AdListRepository adListRepository;
    private final AdMapper adMapper;
    private final AdScore adScore;
    private static final Integer MIN_SCORE_PUBLIC_ADS = 40;
    private static final Integer MIN_SCORE = 0;
    private static final Integer MAX_SCORE = 100;


    public AdListServiceImpl(AdListRepository adListRepository, AdMapper adMapper, AdScore adScore) {
        this.adListRepository = adListRepository;
        this.adMapper = adMapper;
        this.adScore = adScore;
    }

    @Override
    public List<AdVO> listAds() {
        return adListRepository.listAds();
    }

    @Override
    public List<AdVO> listQualityAds() {
        return adListRepository.listAds();
    }

    @Override
    public void scoreAds() {
        List<AdVO> ads = adListRepository.listAds();
        ads.forEach(ad -> {
            Integer score = adScore.calculate(ad);
            if(score == null) {
                score = MIN_SCORE;
            }
            if(score < MIN_SCORE)
                ad.setScore(MIN_SCORE);
            else if(score > MAX_SCORE)
                ad.setScore(MAX_SCORE);
            if(score < MIN_SCORE_PUBLIC_ADS)
                ad.setIrrelevantSince(Calendar.getInstance().getTime());
        });
    }
}
