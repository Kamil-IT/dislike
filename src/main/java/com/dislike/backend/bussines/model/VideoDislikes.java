package com.dislike.backend.bussines.model;

import java.util.List;
import java.util.Set;

public record VideoDislikes(
        String id,
        Set<String> usersWhichDislikes
) {
}
