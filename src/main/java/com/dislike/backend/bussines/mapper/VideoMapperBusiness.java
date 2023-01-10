package com.dislike.backend.bussines.mapper;

import com.dislike.backend.bussines.model.VideoDislikes;
import com.dislike.backend.domain.Video;

import java.util.List;
import java.util.stream.Collectors;

public class VideoMapperBusiness {
    public List<Video> map(List<VideoDislikes> dislikesById, String currentUser) {
        return dislikesById.stream()
                .map(v -> map(v, currentUser))
                .collect(Collectors.toList());
    }
    private Video map(VideoDislikes dislikesById, String currentUser) {
        List<String> usersWhichDislikes = dislikesById.usersWhichDislikes();
        return new Video(
                dislikesById.id(),
                usersWhichDislikes.size(),
                usersWhichDislikes.contains(currentUser));
    }
}
