package com.zhuanghou.videos.web.controller;

import com.zhuanghou.videos.repository.domain.Manager;
import com.zhuanghou.videos.service.ManagerService;
import com.zhuanghou.videos.service.UserService;
import com.zhuanghou.videos.tool.Rsa;
import com.zhuanghou.videos.web.model.LoginResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by duhui on 2017/11/23.
 */

@Controller
@RequestMapping("/admin")
public class AdminLoginController {
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String hello(@RequestParam  String username,@RequestParam  String password){
//        System.out.println(username+"密码"+password);
//        return "admin_login";
//    }
    private Log log =LogFactory.getLog(AdminLoginController.class);

    @Autowired
    private ManagerService managerService;
    @Autowired
    public UserService userService;
    /**
     * [管理员进入登录页面]
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){

           if(Rsa.readToken(request,"admin")){
               return "admin_homePage";
           }else {
               return "admin_login";
           }
    }

    /**
     * [点击登录按钮进行登录]
     * @param username
     * @param password
     * @return LoginResult
     */
    @RequestMapping(value="/loginAjax",method = RequestMethod.POST)
    @ResponseBody
    public LoginResult adminLogin(HttpServletRequest request, HttpServletResponse response ,String username, String password) throws Exception {
        Manager manager = managerService.findManagerByUsername(username);
        System.out.println(manager.getUsername()+"===="+manager.getPassword()+"++++"+password);

        if(manager !=null && manager.isPasswordCorrect(password)){
            //request.getSession().setAttribute("admin","admin");

            String key=manager.getId()+"admin";
            key=manager.getId()+":admin:"+ Rsa.encrypt(key);
            Cookie cookie = new Cookie("admin",key);//创建新cookie
            cookie.setMaxAge(30 * 60);// 设置存在时间为5分钟
            cookie.setPath("/");//设置作用域
            response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端
             return new LoginResult(0,"登录成功！");
        }else{
            return new LoginResult(500,"登录失败！");
        }


    }

    /**
     * 登录成功，进入用户管理界面
     * @return
     */
    @RequestMapping(value = "/homepage",method = RequestMethod.GET)
    public String adminHomePage(){
       // List<User> users=userService.userPage(1,3);

       return "admin_homePage";


    }


}
