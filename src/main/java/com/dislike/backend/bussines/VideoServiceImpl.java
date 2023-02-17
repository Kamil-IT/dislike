package com.dislike.backend.bussines;

import com.dislike.backend.api.model.VideoOperation;
import com.dislike.backend.bussines.mapper.VideoMapperBusiness;
import com.dislike.backend.bussines.model.GetVideoData;
import com.dislike.backend.bussines.model.VideoDataModification;
import com.dislike.backend.domain.Video;
import com.dislike.backend.persistance.video.VideoPersistenceService;
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

    private static Map<VideoOperation, Consumer<VideoDataModification>> videoModification;

    private final VideoPersistenceService videoPersistenceService;
    private final VideoMapperBusiness videoMapper;

    @PostConstruct
    void postConstructor(){
        videoModification = Map.of(
                VideoOperation.DISLIKE, videoPersistenceService::dislike,
                VideoOperation.UNDISLIKE, videoPersistenceService::undislike
        );
    }

    @Override
    public List<Video> getVideos(GetVideoData videoData) {
        return videoMapper.map(
                videoPersistenceService.getDislikesById(videoData),
                videoData.user()
        );
    }

    @Override
    public void updateVideo(VideoDataModification videoDataModification) {
        Optional.ofNullable(videoModification.get(videoDataModification.operation()))
                .orElseThrow(() -> new IllegalArgumentException("Video operation not found: " + videoDataModification))
                .accept(videoDataModification);
    }
}
