package com.dislike.backend.persistance.video;

import com.dislike.backend.bussines.model.VideoDislikes;

import java.util.List;

public interface VideoPersistence {
    void dislike(String id);

    void undislike(String id);

    List<VideoDislikes> getDislikesById(List<String> ids);
}
