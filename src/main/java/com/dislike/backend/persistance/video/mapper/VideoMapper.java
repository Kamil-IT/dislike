package com.dislike.backend.persistance.video.mapper;

import com.dislike.backend.bussines.model.VideoDislikes;
import com.dislike.backend.persistance.video.model.VideoDislikePersistence;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VideoMapper {
    public List<VideoDislikes> map(List<VideoDislikePersistence> allById) {
        return allById.stream()
                .collect(Collectors.groupingBy(VideoDislikePersistence::getId, Collectors.mapping(v -> v.getUserPersistence().getUsername(), Collectors.toList())))
                .entrySet()
                .stream().map(v -> new VideoDislikes(v.getKey(), v.getValue()))
                .collect(Collectors.toList());
    }
}
