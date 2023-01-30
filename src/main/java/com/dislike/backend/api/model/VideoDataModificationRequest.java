package com.dislike.backend.api.model;

public record VideoDataModificationRequest(
        String id,
        VideoOperation operation
) {
}
