package com.dislike.backend.bussines.model;

import com.dislike.backend.api.model.VideoOperation;

public record VideoDataModification(String id, VideoOperation operation, String username) {
}
