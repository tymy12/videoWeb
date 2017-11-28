package com.zhuanghou.videos.repository.repos;

import com.zhuanghou.videos.repository.domain.Video;

import java.util.List;

/**
 * Created by duhui on 2017/11/26.
 */
public interface VideoRepos {
    public List<Video> selectVideo(int page, int pageVideos);
    public int getPageNum(int pageVideoss);
    public  List<Video> videoByQuery(String videoName);
}
