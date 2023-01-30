package com.dislike.backend.bussines;

import com.dislike.backend.bussines.model.GetVideoData;
import com.dislike.backend.bussines.model.VideoDataModification;
import com.dislike.backend.domain.Video;

import java.util.List;

public interface VideoService {
    List<Video> getVideos(GetVideoData ids);

    void updateVideo(VideoDataModification videoDataModification);
}
