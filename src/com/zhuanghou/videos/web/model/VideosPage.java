package com.zhuanghou.videos.web.model;

import com.zhuanghou.videos.repository.domain.Video;

import java.util.List;

/**
 * Created by duhui on 2017/11/26.
 */
public class VideosPage {
    List<Video> videos;
    int pageNum;

    public VideosPage(int pageNum, List<Video> videos){
        this.pageNum=pageNum;
        this.videos=videos;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
