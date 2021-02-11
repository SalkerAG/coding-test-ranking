package com.idealista.infrastructure.api;

import com.idealista.application.service.AdListService;
import com.idealista.domain.mapper.AdMapper;
import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.model.dto.QualityAd;
import com.idealista.domain.model.entity.AdVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AdsController {

    private final AdListService adListService;
    private final AdMapper adMapper;
    private static final Integer MIN_SCORE_PUBLIC_ADS = 40;

    public AdsController(AdListService adListService, AdMapper adMapper) {
        this.adListService = adListService;
        this.adMapper = adMapper;
    }

    @GetMapping("/quality-ads")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        try {
            return ResponseEntity.ok(adListService.listAds().stream()
                    .filter(ad -> (ad.getScore() == null || ad.getScore() < MIN_SCORE_PUBLIC_ADS))
                    .map(adMapper::adVOToQualityAd)
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  @GetMapping("/ads")
  public ResponseEntity<List<PublicAd>> publicListing() {
      try {
          return ResponseEntity.ok(adListService.listAds().stream()
                  .filter(ad -> ad.getScore() != null && ad.getScore() >= MIN_SCORE_PUBLIC_ADS)
                  .sorted(Comparator.comparingInt(AdVO::getScore).reversed())
                  .map(adMapper::adVOToPublicAd)
                  .collect(Collectors.toList()));
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    @PutMapping("/score-ads")
    public ResponseEntity<Void> calculateScore() {
        adListService.scoreAds();
        return ResponseEntity.ok().build();
    }
}
