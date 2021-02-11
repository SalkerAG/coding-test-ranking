package com.idealista.domain.score.rule;

import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.model.entity.PictureVO;
import com.idealista.domain.repository.PictureListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PictureScoreRule implements ScoreRule {

    private static final Integer BASE_RULE_SCORE = 0;
    private static final Integer PICTURE_SD_SCORE = 10;
    private static final Integer PICTURE_HD_SCORE = 20;
    private static final Integer NO_PICTURE_SCORE = -10;

    @Autowired
    private PictureListRepository pictureListRepository;

    public void execute(AdVO ad) {
        Integer pictureScore = ad.getScore() == null ? BASE_RULE_SCORE : ad.getScore();
        if (!ad.getPictures().isEmpty()) {
            ad.getPictures()
                    .forEach(
                            pic -> {
                                Integer score = ad.getScore() == null ? BASE_RULE_SCORE : ad.getScore();
                                PictureVO picture = pictureListRepository.getPictureById(pic);
                                if (picture.getQuality().equals("HD")) ad.setScore(PICTURE_HD_SCORE + score);
                                else ad.setScore(PICTURE_SD_SCORE + score);
                            });
        } else {
            ad.setScore(pictureScore +  NO_PICTURE_SCORE);
        }
    }
}
