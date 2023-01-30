package com.dislike.backend.persistance.video;

import com.dislike.backend.bussines.model.GetVideoData;
import com.dislike.backend.bussines.model.VideoDataModification;
import com.dislike.backend.bussines.model.VideoDislikes;

import java.util.List;

public interface VideoPersistenceService {
    void dislike(VideoDataModification videoDataModification);

    void undislike(VideoDataModification videoDataModification);

    List<VideoDislikes> getDislikesById(GetVideoData ids);
}
