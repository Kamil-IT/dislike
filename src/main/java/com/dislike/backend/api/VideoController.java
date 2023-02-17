package com.dislike.backend.api;

import com.dislike.backend.api.model.GetVideoRequest;
import com.dislike.backend.api.model.VideoDataModificationRequest;
import com.dislike.backend.bussines.VideoService;
import com.dislike.backend.bussines.model.GetVideoData;
import com.dislike.backend.bussines.model.VideoDataModification;
import com.dislike.backend.domain.Video;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/video")
public class VideoController extends BaseController {

    private final VideoService videoService;

    @GetMapping
    public List<Video> getVideos(@RequestParam(value = "ids") List<String> ids) {
        return videoService.getVideos(new GetVideoData(ids, getUsername()));
    }

    @PutMapping
    public void modify(@RequestBody VideoDataModificationRequest videoDataModificationRequest) {
        var id = videoDataModificationRequest.id();
        var operation = videoDataModificationRequest.operation();

        videoService.updateVideo(new VideoDataModification(id, operation, getUsername()));
    }
}
