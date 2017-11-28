package com.zhuanghou.videos.web.controller;

import com.zhuanghou.videos.repository.domain.Video;
import com.zhuanghou.videos.service.VideoService;
import com.zhuanghou.videos.web.model.VideosPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by duhui on 2017/11/23.
 */
@Controller
@RequestMapping("/userManage")
public class MembersManageController {

    public  final int pagevideos = 8;
    @Autowired
    public VideoService videoService;

    @RequestMapping(value = "/homepage", method = RequestMethod.POST)
    @ResponseBody
    public VideosPage adminHomePage(String page) {



        List<Object> objects;

        int nowpage = Integer.valueOf(page);
        System.out.println("页数：" + page);
        objects = videoService.videoPage(nowpage, pagevideos);
        int pagenum = (int) objects.get(0);
        List<Video> users = (List<Video>) objects.get(1);
        //return "admin_homePage";
        if (pagenum % pagevideos == 0) {
            return new VideosPage(pagenum / pagevideos, users);
        } else {
            return new VideosPage((pagenum / pagevideos) + 1, users);
        }


    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String adminHomePages() {


        return "user_homePage";


    }

    @RequestMapping(value = "/videoQuery", method = RequestMethod.POST)
    @ResponseBody
    public VideosPage userManageVideoQuery(String videoname) {


        List<Video> videos = videoService.videoByQuery(videoname);
        return new VideosPage( 1, videos);

    }
    @RequestMapping(value = "/play",method = RequestMethod.GET)
    public String videoPlay(){
        return "videoPlay";
    }




}
