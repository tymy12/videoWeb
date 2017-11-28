package com.zhuanghou.videos.repository.repos;

import com.zhuanghou.videos.repository.domain.Manager;

/**
 * Created by duhui on 2017/11/24.
 */
public interface ManagerRepos {
    public Manager queryBy(String username);
}
