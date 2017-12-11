package com.zhuanghou.videos.repository.domain;

/**
 * Created by duhui on 2017/11/24.
 */
public class Manager extends Admin {
    private String username;
    private String password;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPasswordCorrect(String password) {
        if (password != null && password.equals(this.password)) {
            return true;
        }
        return false;
    }
}
