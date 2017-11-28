package com.zhuanghou.videos.service.impl;

import com.zhuanghou.videos.repository.domain.Video;
import com.zhuanghou.videos.repository.repos.VideoRepos;
import com.zhuanghou.videos.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duhui on 2017/11/26.
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    public VideoRepos videoRepos;

    @Override
    public List<Object> videoPage(int page, int pageVideos) {
        List<Object> objects=new ArrayList();
        objects.add(videoRepos.getPageNum(pageVideos));
        objects.add(videoRepos.selectVideo(page,pageVideos));

        return objects;
    }

    @Override
    public List<Video> videoByQuery(String videoName) {
        return videoRepos.videoByQuery(videoName);
    }
}
