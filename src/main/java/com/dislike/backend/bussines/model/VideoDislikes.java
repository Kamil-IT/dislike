package com.dislike.backend.bussines.model;

import java.util.List;

public record VideoDislikes(
        String id,
        List<String> usersWhichDislikes
) {
}
