package com.idealista.domain.repository;

import com.idealista.domain.model.entity.PictureVO;

import java.util.List;

public interface PictureListRepository {
    List<PictureVO> listPictures();
}
