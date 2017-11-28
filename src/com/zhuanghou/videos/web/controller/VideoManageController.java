package com.zhuanghou.videos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by duhui on 2017/11/23.
 */
@Controller
@RequestMapping("/video")
public class VideoManageController {

    @RequestMapping(value = "/play",method = RequestMethod.GET)
    public String videoPlay(){
        return "videoPlay";
    }


}
