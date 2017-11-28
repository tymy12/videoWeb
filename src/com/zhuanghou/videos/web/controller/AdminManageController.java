package com.zhuanghou.videos.web.controller;

import com.zhuanghou.videos.repository.domain.User;
import com.zhuanghou.videos.service.UserService;
import com.zhuanghou.videos.web.model.AddUser;
import com.zhuanghou.videos.web.model.UsersPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duhui on 2017/11/26.
 */
@Controller
@RequestMapping("/adminManage")
public class AdminManageController {
    public  final  int pageusers = 8;

    @Autowired
    public UserService userService;

    /**
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/homepage", method = RequestMethod.POST)
    @ResponseBody
    public UsersPage adminHomePage(String page) {


        List<Object> objects;

        int nowpage = Integer.valueOf(page);
        objects = userService.userPage(nowpage, pageusers);

        int pagenum = (int) objects.get(0);

        List<User> users = (List<User>) objects.get(1);

        //return "admin_homePage";
        if (pagenum % pageusers == 0) {
            return new UsersPage(pagenum / pageusers, users);
        } else {
            return new UsersPage((pagenum / pageusers) + 1, users);
        }

    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String adminHomePages() {


        return "admin_homePage";


    }


    @RequestMapping(value = "/userQuery", method = RequestMethod.POST)
    @ResponseBody
    public UsersPage userManageVideoQuery(String usermoibel) {
        List<User> users=userService.findManagerByUsers(usermoibel);
        System.out.println(usermoibel);

        return new UsersPage(1,users);
    }

    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    @ResponseBody
    public AddUser userManageAddUser(String username, String usermobile){
     boolean b=userService.insertUser(username,usermobile);

        return new AddUser(b);

    }
}
