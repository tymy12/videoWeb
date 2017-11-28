package com.zhuanghou.videos.repository.repos.impl;

import com.mysql.jdbc.ResultSet;
import com.sun.org.glassfish.external.statistics.annotations.Reset;
import com.zhuanghou.videos.repository.domain.Video;
import com.zhuanghou.videos.repository.repos.MysqlJDBC;
import com.zhuanghou.videos.repository.repos.VideoRepos;
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
    ResultSet rs;
    @Autowired
    public static VideoRepos videoRepos;
    @Override
    public List<Video> selectVideo(int page, int pageVideos) {
        List<Video> videos=new ArrayList();
        String sql="select *from video limit ?,?";
        String[] strings ={((page-1)*pageVideos)+"",pageVideos+""};

        rs= (ResultSet) MysqlJDBC.select(sql,strings);

        try {
            while (rs.next()){
                Video video=new Video();
                video.setVideoName(rs.getString(2));
                video.setVideoUrl(rs.getString(3));
                video.setVideoIntroduce(rs.getString(4));
                video.setDate(rs.getString(5));
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
        rs= (ResultSet) MysqlJDBC.select(sql,strings);
        int pageNum = 0;
        try {
            rs.next();
            pageNum=Integer.valueOf(rs.getString(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageNum;
    }

    @Override
    public List<Video> videoByQuery(String videoName) {
        String sql="select *from video where videoname= ? ";
        String[] strings={videoName};
        List<Video> videos=new ArrayList();


        rs= (ResultSet) MysqlJDBC.select(sql,strings);

        try {
            while (rs.next()){
                Video video=new Video();
                video.setVideoName(rs.getString(2));
                video.setVideoUrl(rs.getString(3));
                video.setVideoIntroduce(rs.getString(4));
                video.setDate(rs.getString(5));
                videos.add(video);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return videos;
    }
}
