package com.idealista.application.service;

import com.idealista.domain.model.entity.PictureVO;
import com.idealista.domain.repository.PictureListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureListServiceImpl implements PictureListService {
    private PictureListRepository pictureListRepository;

    public PictureListServiceImpl(PictureListRepository pictureListRepository) {
        this.pictureListRepository = pictureListRepository;
    }

    @Override
    public List<PictureVO> listPictures() {
        return pictureListRepository.listPictures();
    }
}
