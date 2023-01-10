package com.dislike.backend.bussines.model;

import com.dislike.backend.api.model.VideoOperation;

public record VideoDataModificationBussines(String id, VideoOperation operation, String user) {
}
