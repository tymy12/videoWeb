package com.zhuanghou.videos.web.controller;


import com.zhuanghou.videos.repository.domain.User;
import com.zhuanghou.videos.service.UserService;
import com.zhuanghou.videos.web.model.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")

public class MemberLoginController {

    @Autowired
    public  UserService userService;

    /**
     * [用户进入登录页面]
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "user_login";
    }

    /**
     * [用户点击登录按钮进行登录]
     * @param mobile
     * @return LoginResult
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public LoginResult login(HttpServletRequest request, HttpServletResponse response , String mobile){
        List<User> user=userService.findManagerByUsername(mobile);
        if(user.size()>0){

            Cookie cookie = new Cookie("user","user");
            cookie.setMaxAge(30 * 60);// 设置为30min
            cookie.setPath("/");
            System.out.println("已添加===============");
            response.addCookie(cookie);
            return new LoginResult(0,"登录成功！");
        }else {
            return new LoginResult(500,"登录失败！");

        }

    }


    /**
     * [用户登录成功，进入视频列表页面]
     * @return
     */
    @RequestMapping(value = "/homePage" ,method = RequestMethod.GET)
    public String homePage(){
        return "user_homePage";
    }
}
