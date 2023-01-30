package com.dislike.backend.persistance.video;

import com.dislike.backend.bussines.model.GetVideoData;
import com.dislike.backend.bussines.model.VideoDataModification;
import com.dislike.backend.bussines.model.VideoDislikes;
import com.dislike.backend.persistance.user.UserRepository;
import com.dislike.backend.persistance.user.model.UserPersistence;
import com.dislike.backend.persistance.video.mapper.VideoMapper;
import com.dislike.backend.persistance.video.model.VideoDislikePersistence;
import com.dislike.backend.persistance.video.model.VideoPersistence;
import com.dislike.backend.persistance.video.repository.VideoRatingRepository;
import com.dislike.backend.persistance.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoPersistenceServiceImpl implements VideoPersistenceService {

    private final VideoRatingRepository videoRatingRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final VideoMapper videoMapper;

    @Override
    public void dislike(VideoDataModification data) {
        videoRatingRepository.findByVideoIdAndUsername(data.id(), data.username())
                        .ifPresentOrElse(
                                (video) -> log.info("Cannot dislike beacuse this video is alreaty disliked: " + video),
                                () -> videoRatingRepository.save(buildVideoDislikePersistance(data)));
    }

    private VideoDislikePersistence buildVideoDislikePersistance(VideoDataModification data) {
        VideoPersistence video = videoRepository.findById(data.id()).orElseThrow();
        UserPersistence userPersistence = userRepository.findById(data.username()).orElseThrow();

        return VideoDislikePersistence.builder()
                .videoPersistence(video)
                .userPersistence(userPersistence)
                .build();
    }

    @Override
    public void undislike(VideoDataModification data) {
        videoRatingRepository.findByVideoIdAndUsername(data.id(), data.username())
                .ifPresent(videoRatingRepository::delete);
    }

    @Override
    public List<VideoDislikes> getDislikesById(GetVideoData data) {
        return videoMapper.map(videoRatingRepository.findAllById(data.ids()));
    }
}
