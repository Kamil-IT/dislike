package com.dislike.backend.api;

import com.dislike.backend.api.model.VideoDataModification;
import com.dislike.backend.bussines.VideoService;
import com.dislike.backend.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public List<Video> getVideos(List<String> ids) {
        String currentUser = "TODO: !!!!!";
        return videoService.getVideos(ids, currentUser);
    }

    @PutMapping
    public void modify(VideoDataModification videoDataModification) {
        String currentUser = "TODO: !!!!!";
        videoService.updateVideo(videoDataModification, currentUser);
    }

}
