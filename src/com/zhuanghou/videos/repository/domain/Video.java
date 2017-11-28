package com.zhuanghou.videos.repository.domain;

/**
 * Created by duhui on 2017/11/26.
 */
public class Video {
    private String videoName;
    private String videoUrl;
    private  String videoIntroduce;
    private  String date;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoIntroduce() {
        return videoIntroduce;
    }

    public void setVideoIntroduce(String videoIntroduce) {
        this.videoIntroduce = videoIntroduce;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
