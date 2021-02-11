package com.idealista.domain.score.rule;

import com.idealista.domain.model.entity.AdVO;
import org.springframework.stereotype.Component;

@Component
public class DescriptionScoreRule implements ScoreRule {
    private static final Integer HAS_DESCRIPTION = 5;
    private static final Integer BASE_RULE_SCORE = 0;

    @Override
    public void execute(AdVO ad) {
        Integer pictureScore = ad.getScore() == null ? BASE_RULE_SCORE : ad.getScore();
        if (!ad.getDescription().isEmpty()) ad.setScore(HAS_DESCRIPTION + pictureScore);
        else ad.setScore(pictureScore + BASE_RULE_SCORE);
    }

}
