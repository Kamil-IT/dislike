package com.dislike.backend.bussines.model;

import com.dislike.backend.api.model.VideoOperation;

import java.util.List;

public record GetVideoData(List<String> ids, String user) {
}
