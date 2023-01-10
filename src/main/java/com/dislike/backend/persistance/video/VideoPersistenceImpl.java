package com.dislike.backend.persistance.video;

import com.dislike.backend.bussines.model.VideoDislikes;
import com.dislike.backend.persistance.video.mapper.VideoMapper;
import com.dislike.backend.persistance.video.repository.VideoRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoPersistenceImpl implements VideoPersistence {

    private final VideoRatingRepository videoRatingRepository;
    private final VideoMapper videoMapper;

    @Override
    public void dislike(String id) {
        videoRatingRepository
    }

    @Override
    public void undislike(String id) {

    }

    @Override
    public List<VideoDislikes> getDislikesById(List<String> ids) {
        return videoMapper.map(videoRatingRepository.findAllById(ids));
    }
}
