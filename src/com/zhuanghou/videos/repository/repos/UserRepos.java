package com.zhuanghou.videos.repository.repos;

import com.zhuanghou.videos.repository.domain.Manager;
import com.zhuanghou.videos.repository.domain.User;

import java.util.List;

/**
 * Created by duhui on 2017/11/25
 */
public interface UserRepos {
    public List<User> selectUser(int page,int pageUsers);
    public int getPageNum(int pageUsers);
    public  boolean deleteUser(String mobile);
    public  boolean updateUser(String name,String mobile,String usedmobile );
    public boolean insertUser(String name, String mobile);
    public  List<User> queryBy(String mobile);
    public  List<User> queryuser(String key);



}
