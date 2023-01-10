package com.dislike.backend.bussines;

import com.dislike.backend.api.model.VideoDataModification;
import com.dislike.backend.api.model.VideoOperation;
import com.dislike.backend.bussines.mapper.VideoMapperBusiness;
import com.dislike.backend.bussines.model.VideoDataModificationBussines;
import com.dislike.backend.domain.Video;
import com.dislike.backend.persistance.video.VideoPersistence;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@RequiredArgsConstructor
@Service
public class VideoServiceImpl implements VideoService {

    private static Map<VideoOperation, Consumer<String>> videoModification;

    private final VideoPersistence videoPersistence;
    private final VideoMapperBusiness videoMapper;

    @PostConstruct
    void postConstructor(){
        videoModification = Map.of(
                VideoOperation.DISLIKE, videoPersistence::dislike,
                VideoOperation.UNDISLIKE, videoPersistence::undislike
        );
    }

    @Override
    public List<Video> getVideos(List<String> ids, String currentUser) {
        return videoMapper.map(videoPersistence.getDislikesById(ids), currentUser);
    }

    @Override
    public void updateVideo(VideoDataModification videoDataModification, String currentUser) {
        new VideoDataModificationBussines(videoDataModification.id(), videoDataModification.operation(), currentUser);
        Optional.ofNullable(videoModification.get(videoDataModification.operation()))
                .orElseThrow(() -> new IllegalArgumentException("Video operation not found: " + videoDataModification))
                .accept(videoDataModification.id());
    }
}
