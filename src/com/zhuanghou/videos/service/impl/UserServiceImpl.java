package com.zhuanghou.videos.service.impl;

import com.zhuanghou.videos.repository.domain.User;
import com.zhuanghou.videos.repository.repos.UserRepos;
import com.zhuanghou.videos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duhui on 2017/11/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepos userRepos;


    @Override
    public List<Object> userPage(int page, int pageUsers) {
        List<Object> userpage =new ArrayList();
        userpage.add(userRepos.getPageNum(pageUsers));
        userpage.add(userRepos.selectUser(page,pageUsers));
        return userpage;
    }

    @Override
    public List<User> findManagerByUsername(String mobile) {

        return userRepos.queryBy(mobile);
    }

    @Override
    public List<User> findManagerByUsers(String key) {
        return userRepos.queryuser(key);
    }

    @Override
    public boolean insertUser(String username, String usermobile) {
        return userRepos.insertUser(username,usermobile);
    }


}
