package com.idealista.domain.mapper;

import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.model.dto.QualityAd;
import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.repository.AdListRepository;
import com.idealista.domain.repository.PictureListRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class AdMapperImpl implements AdMapper {

    private AdListRepository adListRepository;
    private PictureListRepository pictureListRepository;

    public AdMapperImpl(AdListRepository adListRepository, PictureListRepository pictureListRepository) {
        this.adListRepository = adListRepository;
        this.pictureListRepository = pictureListRepository;
    }

    @Override
    public PublicAd adVOToPublicAd(AdVO ad) {
        PublicAd publicAd = new PublicAd();
        publicAd.setId(ad.getId());
        publicAd.setTypology(ad.getTypology());
        publicAd.setDescription(ad.getDescription());
        publicAd.setPictureUrls(picturesUrls(ad));
        publicAd.setHouseSize(ad.getHouseSize());
        publicAd.setGardenSize(ad.getGardenSize());
        return publicAd;
    }

    @Override
    public QualityAd adVOToQualityAd(AdVO ad) {
        QualityAd qualityAd = new QualityAd();
        qualityAd.setId(ad.getId());
        qualityAd.setTypology(ad.getTypology());
        qualityAd.setDescription(ad.getDescription());
        qualityAd.setPictureUrls(picturesUrls(ad));
        qualityAd.setHouseSize(ad.getHouseSize());
        qualityAd.setGardenSize(ad.getGardenSize());
        qualityAd.setScore(ad.getScore());
        qualityAd.setIrrelevantSince(ad.getIrrelevantSince());
        return qualityAd;
    }

    public List<String> picturesUrls(AdVO ad) {
        List<String> picturesUrl = new ArrayList<>();
        if (ad.getPictures() != Collections.<Integer>emptyList()) {
            ad.getPictures()
                .forEach(
                    id -> {
                        picturesUrl.add(
                            Objects.requireNonNull(
                                    pictureListRepository.listPictures().stream()
                                        .filter(pic -> pic.getId().equals(id))
                                        .findFirst()
                                        .orElse(null))
                                    .getUrl());
                    }
                );
        }
        return picturesUrl;
    }
}
