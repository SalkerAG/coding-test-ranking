package com.idealista.domain.score.rule;

import com.idealista.domain.model.entity.AdVO;
import org.springframework.stereotype.Component;

@Component
public class TypologyScoreRule implements ScoreRule {
    private static final Integer BASE_RULE_SCORE = 0;
    private static final Integer TYPOLOGY_SCORE = 40;
    private static final String GARAGE = "GARAGE";
    private static final String CHALET = "CHALET";
    private static final String FLAT = "FLAT";

    @Override
    public void execute(AdVO ad) {
        Integer pictureScore = ad.getScore() == null ? BASE_RULE_SCORE : ad.getScore();
        if(isScorable(ad))
            ad.setScore(TYPOLOGY_SCORE + pictureScore);
    }

    //TODO Refactor extraer métodos
    boolean isScorable(AdVO ad) {
        boolean condition = false;
        if ((ad.getTypology().equals(GARAGE) && !ad.getPictures().isEmpty())
                || (ad.getTypology().equals(FLAT)
                && !ad.getPictures().isEmpty()
                && !ad.getDescription().isEmpty()
                && ad.getHouseSize() != null)
                || (ad.getTypology().equals(CHALET)
                && !ad.getPictures().isEmpty()
                && !ad.getDescription().isEmpty()
                && ad.getHouseSize() != null
                && ad.getGardenSize() != null)) condition = true;
        return condition;
    }
}
