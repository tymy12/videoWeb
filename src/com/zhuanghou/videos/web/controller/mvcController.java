package com.zhuanghou.videos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by duhui on 2017/11/24.
 */
@Controller
@RequestMapping("/mvc")
public class mvcController {

    @RequestMapping("/untitled")
    public String hello(){
        System.out.println("untitled");
        return "untitled";
    }
}