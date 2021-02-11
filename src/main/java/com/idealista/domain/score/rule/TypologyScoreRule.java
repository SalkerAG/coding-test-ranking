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
        else ad.setScore(BASE_RULE_SCORE + pictureScore);
    }

    boolean isScorable(AdVO ad) {
        boolean condition = false;
        if ((isGarage(ad) && havePicture(ad))
                || (isFlat(ad)
                && havePicture(ad)
                && haveDescription(ad)
                && haveHouseSize(ad))
                || (isChalet(ad)
                && havePicture(ad)
                && haveDescription(ad)
                && haveHouseSize(ad)
                && haveGardenSize(ad))) condition = true;
        return condition;
    }

    boolean isGarage(AdVO ad) {
        return ad.getTypology().equals(GARAGE);
    }

    boolean isFlat(AdVO ad) {
        return ad.getTypology().equals(FLAT);
    }

    boolean isChalet(AdVO ad) {
        return ad.getTypology().equals(CHALET);
    }

    boolean havePicture(AdVO ad) {
        return !ad.getPictures().isEmpty();
    }

    boolean haveDescription(AdVO ad) {
        return !ad.getDescription().isEmpty();
    }

    boolean haveHouseSize(AdVO ad) {
        return ad.getHouseSize() != null;
    }

    boolean haveGardenSize(AdVO ad) {
        return ad.getGardenSize() != null;
    }
}
