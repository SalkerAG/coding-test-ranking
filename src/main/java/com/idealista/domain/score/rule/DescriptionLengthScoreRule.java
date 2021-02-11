package com.idealista.domain.score.rule;

import com.idealista.domain.model.entity.AdVO;
import org.springframework.stereotype.Component;

@Component
public class DescriptionLengthScoreRule implements ScoreRule {
    private static final Integer BASE_RULE_SCORE = 0;
    private static final Integer MIN_LENGTH_FLAT = 20;
    private static final Integer MID_LENGTH_FLAT = 49;
    private static final Integer MAX_LENGTH_FLAT = 50;
    private static final Integer MIN_LENGTH_CHALET= 50;
    private static final Integer MIN_SCORE_DESCRIPTION_FLAT = 10;
    private static final Integer MAX_SCORE_DESCRIPTION_FLAT = 30;
    private static final Integer MAX_SCORE_DESCRIPTION_CHALET = 20;
    private static final String FLAT = "FLAT";
    private static final String CHALET = "CHALET";

    @Override
    public void execute(AdVO ad) {
        Integer pictureScore = ad.getScore() == null ? BASE_RULE_SCORE : ad.getScore();
        if (!ad.getDescription().isEmpty() && (ad.getTypology().equals(FLAT) || ad.getTypology().equals(CHALET))) {
            String[] words = ad.getDescription().split("\\s+");
            if (ad.getTypology().equals(FLAT)) {
                if (words.length >= MIN_LENGTH_FLAT && words.length <= MID_LENGTH_FLAT)
                    ad.setScore(MIN_SCORE_DESCRIPTION_FLAT + pictureScore);
                else if (words.length >= MAX_LENGTH_FLAT)
                    ad.setScore(MAX_SCORE_DESCRIPTION_FLAT + pictureScore);
                else
                    ad.setScore(pictureScore + BASE_RULE_SCORE);
            } else{
                if (ad.getTypology().equals(CHALET) && words.length > MIN_LENGTH_CHALET)
                    ad.setScore(MAX_SCORE_DESCRIPTION_CHALET + pictureScore);
                else
                    ad.setScore(BASE_RULE_SCORE + pictureScore);
            }
        }
        else{
            ad.setScore(pictureScore + BASE_RULE_SCORE);
        }
    }
}
