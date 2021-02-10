package com.idealista.infrastructure.api;

import com.idealista.application.service.AdListService;
import com.idealista.domain.mapper.AdMapper;
import com.idealista.domain.model.dto.PublicAd;
import com.idealista.domain.model.dto.QualityAd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdsController {

    private AdListService adListService;

    public AdsController(AdListService adListService) {
        this.adListService = adListService;
    }

    @GetMapping("/quality-ads")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        try {
            return ResponseEntity.ok(adListService.listQualityAds());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  @GetMapping("/ads")
  public ResponseEntity<List<PublicAd>> publicListing() {
      try {
          return ResponseEntity.ok(adListService.listAds());
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    // TODO añade url del endpoint
    public ResponseEntity<Void> calculateScore() {
        // TODO rellena el cuerpo del método
        return ResponseEntity.notFound().build();
    }
}
