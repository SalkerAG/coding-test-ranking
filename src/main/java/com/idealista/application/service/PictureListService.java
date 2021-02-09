package com.idealista.application.service;

import com.idealista.domain.model.entity.AdVO;
import com.idealista.domain.model.entity.PictureVO;

import java.util.List;

public interface PictureListService {
    List<PictureVO> listPictures();
}
