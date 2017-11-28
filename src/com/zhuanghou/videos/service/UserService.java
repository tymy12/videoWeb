package com.zhuanghou.videos.service;

import com.zhuanghou.videos.repository.domain.Manager;
import com.zhuanghou.videos.repository.domain.User;
import sun.text.normalizer.SymbolTable;

import java.util.List;

/**
 * Created by duhui on 2017/11/25.
 */
public interface UserService {
    public List<Object> userPage(int page,int pageUsers);
    public List<User> findManagerByUsername(String mobile);
    public List<User> findManagerByUsers(String key);

    public boolean insertUser(String username, String usermobile);
}
