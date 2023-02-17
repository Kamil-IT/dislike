package com.dislike.backend.persistance.video;

import com.dislike.backend.bussines.model.GetVideoData;
import com.dislike.backend.bussines.model.VideoDataModification;
import com.dislike.backend.bussines.model.VideoDislikes;
import com.dislike.backend.persistance.user.UserRepository;
import com.dislike.backend.persistance.user.model.UserPersistence;
import com.dislike.backend.persistance.video.model.DislikePersistence;
import com.dislike.backend.persistance.video.model.VideoPersistence;
import com.dislike.backend.persistance.video.repository.DislikeRepository;
import com.dislike.backend.persistance.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoPersistenceServiceImpl implements VideoPersistenceService {

    private final DislikeRepository dislikeRepository;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Override
    public void dislike(VideoDataModification data) {
        videoRepository.findById(data.id())
                .filter(containsUsername(data))
                .ifPresentOrElse(
                        (video) -> log.info("Cannot dislike beacuse this video is alreaty disliked: " + video),
                        () -> saveDislike(data));
    }

    private static Predicate<VideoPersistence> containsUsername(VideoDataModification data) {
        return video -> video.getDislikePersistences()
                .stream()
                .map(DislikePersistence::getUserPersistence)
                .map(UserPersistence::getUsername)
                .anyMatch(username -> data.username().equals(username));
    }

    private void saveDislike(VideoDataModification data) {
        DislikePersistence entity = buildVideoDislikePersistance(data);
        VideoPersistence video = videoRepository.findById(data.id())
                .orElse(videoRepository.save(VideoPersistence.of(data)));
        video.getDislikePersistences().add(entity);
        videoRepository.save(video);
    }

    private DislikePersistence buildVideoDislikePersistance(VideoDataModification data) {
        VideoPersistence video = videoRepository.findById(data.id())
                .orElse(videoRepository.save(VideoPersistence.of(data)));
        UserPersistence userPersistence = userRepository.findById(data.username()).orElseThrow();

        return DislikePersistence.builder()
                .id(UUID.randomUUID().toString())
                .videoPersistence(video)
                .userPersistence(userPersistence)
                .build();
    }

    @Override
    public void undislike(VideoDataModification data) {
        dislikeRepository.findByVideoIdAndUsername(data.id(), data.username())
                .ifPresent(dislike -> {
                    VideoPersistence videoPersistence = dislike.getVideoPersistence();
                    videoPersistence.getDislikePersistences().remove(dislike);
                    videoRepository.save(videoPersistence);
                });
    }

    @Override
    public List<VideoDislikes> getDislikesById(GetVideoData data) {
        List<VideoPersistence> foundVideos = videoRepository.findAllById(data.ids());

        Set<String> foundVideoIds = foundVideos.stream()
                .map(VideoPersistence::getId)
                .collect(Collectors.toSet());

        List<VideoPersistence> createdVideos = data.ids().stream()
                .filter(id -> !foundVideoIds.contains(id))
                .map(id -> new VideoPersistence(id, null, new ArrayList<>()))
                .map(videoRepository::save)
                .toList();

        return Stream.concat(foundVideos.stream(), createdVideos.stream())
                .map(video -> new VideoDislikes(video.getId(), extractUsernames(video)))
                .toList();
    }

    private static Set<String> extractUsernames(VideoPersistence video) {
        return video.getDislikePersistences().stream()
                .map(DislikePersistence::getUserPersistence)
                .map(UserPersistence::getUsername)
                .collect(Collectors.toSet());
    }
}
