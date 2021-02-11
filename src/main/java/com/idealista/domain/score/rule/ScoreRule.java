package com.idealista.domain.score.rule;

import com.idealista.domain.model.entity.AdVO;

public interface ScoreRule {
    void execute(AdVO ad);
}
