package com.idealista.domain.score;

import com.idealista.domain.model.entity.AdVO;

public interface AdScore {
    Integer calculate(AdVO ad);
}
