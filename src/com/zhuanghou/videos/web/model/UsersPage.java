package com.zhuanghou.videos.web.model;

import com.zhuanghou.videos.repository.domain.User;

import java.util.List;

/**
 * Created by duhui on 2017/11/25.
 */
public class UsersPage {
    List<User> users;

    int pageNum;

    public UsersPage(int pugenum, List<User> users){
        this.pageNum=pugenum;
        this.users=users;

    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
