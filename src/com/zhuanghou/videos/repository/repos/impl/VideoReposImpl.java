package com.zhuanghou.videos.repository.repos.impl;

import com.mchange.v2.c3p0.impl.NewProxyResultSet;
import com.mysql.jdbc.ResultSet;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import com.zhuanghou.videos.repository.domain.Video;
import com.zhuanghou.videos.repository.repos.MysqlJDBC;
import com.zhuanghou.videos.repository.repos.VideoRepos;
import org.apache.commons.dbcp.DelegatingResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duhui on 2017/11/26.
 */
@Repository
public class VideoReposImpl implements VideoRepos {
    @Autowired
    public static VideoRepos videoRepos;
    @Override
    public List<Video> selectVideo(int page, int pageVideos) {
        List<Video> videos=new ArrayList();
        String sql="select *from video limit ?,?";
        String[] strings ={((page-1)*pageVideos)+"",pageVideos+""};

        DelegatingResultSet newProxyResultSet =null;
        newProxyResultSet = (DelegatingResultSet) MysqlJDBC.select(sql, strings)[0];

        try {
            while (newProxyResultSet.next()){
                Video video=new Video();
                video.setVideoName(newProxyResultSet.getString(2));
                video.setVideoUrl(newProxyResultSet.getString(3));
                video.setVideoIntroduce(newProxyResultSet.getString(4));
                video.setDate(newProxyResultSet.getString(5));
                videos.add(video);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return videos;
    }

    @Override
    public int getPageNum(int pageVideos) {
        String sql="select count(*) from video";
        String[] strings={};

        DelegatingResultSet newProxyResultSet =null;
        Object[] objects=MysqlJDBC.select(sql,strings);
        newProxyResultSet = (DelegatingResultSet) objects[0];

        int pageNum = 0;
        try {
            newProxyResultSet.next();
            pageNum=Integer.valueOf(newProxyResultSet.getString(1));

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
                MysqlJDBC.close(objects);
        }
        return pageNum;
    }

    @Override
    public List<Video> videoByQuery(String videoName) {
        String sql="select *from video where videoname= ? ";
        String[] strings={videoName};
        List<Video> videos=new ArrayList();


        DelegatingResultSet newProxyResultSet =null;

        Object[] objects=MysqlJDBC.select(sql,strings);
        newProxyResultSet = (DelegatingResultSet) objects[0];

        try {
            while (newProxyResultSet.next()){
                Video video=new Video();
                video.setVideoName(newProxyResultSet.getString(2));
                video.setVideoUrl(newProxyResultSet.getString(3));
                video.setVideoIntroduce(newProxyResultSet.getString(4));
                video.setDate(newProxyResultSet.getString(5));
                videos.add(video);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MysqlJDBC.close(objects);
        }

        return videos;
    }
}
