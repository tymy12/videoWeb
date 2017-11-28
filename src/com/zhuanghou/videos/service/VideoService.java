package com.zhuanghou.videos.service;

import com.zhuanghou.videos.repository.domain.Video;

import java.util.List;

/**
 * Created by duhui on 2017/11/26.
 */
public interface VideoService {
    public List<Object> videoPage(int page, int pageUsers);
    public  List<Video> videoByQuery(String videoName);

}
