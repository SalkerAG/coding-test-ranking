package com.idealista.domain.score.rule;

import com.idealista.domain.model.entity.AdVO;
import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;

@Component
public class DescriptionSpecialsWordsScore implements ScoreRule {
    private final List<String> specialWords = Arrays.asList("luminoso", "nuevo", "centrico", "reformado", "atico");
    private static final Integer SPECIAL_WORD_SCORE = 5;
    private static final Integer BASE_RULE_SCORE = 0;

    @Override
    public void execute(AdVO ad) {
        String description =
                Normalizer.normalize(ad.getDescription(), Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        specialWords.forEach(word -> {
            Integer pictureScore = ad.getScore() == null ? BASE_RULE_SCORE : ad.getScore();
            if(description.toLowerCase().contains(word)) {
                ad.setScore(SPECIAL_WORD_SCORE + pictureScore);
            }
        });
    }
}
