package com.idealista.domain.score;

import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.repository.PictureListRepository;
import com.idealista.domain.score.rule.ScoreRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdScoreImpl implements AdScore {

    @Autowired
    private PictureListRepository pictureListRepository;

    @Autowired
    private List<ScoreRule> scoreRules;

    @Override
    public Integer calculate(AdVO ad) {
        applyRules(ad);
        return ad.getScore();
    }

    private void applyRules(AdVO ad) {
        scoreRules.forEach(rule -> rule.execute(ad));
    }
}
