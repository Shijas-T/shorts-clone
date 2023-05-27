package com.example.shortsclone;

public class ShortsModel {

    private String mediaUrl;
    private String thumbnail;

    public ShortsModel(String mediaUrl, String thumbnail) {
        this.mediaUrl = mediaUrl;
        this.thumbnail = thumbnail;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
