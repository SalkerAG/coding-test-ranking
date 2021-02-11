package com.idealista.domain.repository;

import com.idealista.domain.model.entity.PictureVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureListRepository {
    List<PictureVO> listPictures();
    PictureVO getPictureById(Integer id);
}
