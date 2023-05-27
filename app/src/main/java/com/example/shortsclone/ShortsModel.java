package com.example.shortsclone;

import java.io.Serializable;

public class ShortsModel implements Serializable{

    private String mediaUrl;
    private String thumbnail;
    private String title;
    private String description;

    public ShortsModel(String mediaUrl, String thumbnail, String title, String description) {
        this.mediaUrl = mediaUrl;
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
