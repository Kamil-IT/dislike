package com.dislike.backend.domain;

public record Video(
        String id,
        Integer dislikes,
        Boolean hasBeenDislikedByUser
) { }
