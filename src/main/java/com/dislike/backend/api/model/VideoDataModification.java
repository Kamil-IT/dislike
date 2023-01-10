package com.dislike.backend.api.model;

public record VideoDataModification(
        String id,
        VideoOperation operation
) {
}
