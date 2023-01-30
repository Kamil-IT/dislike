package com.dislike.backend.api;

import com.dislike.backend.api.model.VideoDataModificationRequest;
import com.dislike.backend.bussines.VideoService;
import com.dislike.backend.bussines.model.GetVideoData;
import com.dislike.backend.bussines.model.VideoDataModification;
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
public class VideoController extends BaseController {

    private final VideoService videoService;

    @GetMapping
    public List<Video> getVideos(List<String> ids) {
        return videoService.getVideos(new GetVideoData(ids, getUsername()));
    }

    @PutMapping
    public void modify(VideoDataModificationRequest videoDataModificationRequest) {
        var id = videoDataModificationRequest.id();
        var operation = videoDataModificationRequest.operation();

        videoService.updateVideo(new VideoDataModification(id, operation, getUsername()));
    }
}
