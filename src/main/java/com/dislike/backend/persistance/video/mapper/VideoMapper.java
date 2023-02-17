package com.dislike.backend.persistance.video.mapper;

import com.dislike.backend.bussines.model.VideoDislikes;
import com.dislike.backend.persistance.video.model.DislikePersistence;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VideoMapper {
    public List<VideoDislikes> map(List<DislikePersistence> allById) {
        return allById.stream()
                .collect(Collectors.groupingBy(DislikePersistence::getId, Collectors.mapping(v -> v.getUserPersistence().getUsername(), Collectors.toList())))
                .entrySet()
                .stream().map(v -> new VideoDislikes(v.getKey(), new HashSet<>(v.getValue())))
                .collect(Collectors.toList());
    }
}
