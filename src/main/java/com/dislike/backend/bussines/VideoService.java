package com.dislike.backend.bussines;

import com.dislike.backend.api.model.VideoDataModification;
import com.dislike.backend.domain.Video;

import java.util.List;

public interface VideoService {
    List<Video> getVideos(List<String> ids, String currentUser);

    void updateVideo(VideoDataModification videoDataModification, String currentUser);
}
