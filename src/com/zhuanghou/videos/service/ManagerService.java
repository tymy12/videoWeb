package com.zhuanghou.videos.service;

import com.zhuanghou.videos.repository.domain.Manager;

/**
 * Created by duhui on 2017/11/24.
 */

public interface ManagerService {
    public Manager findManagerByUsername(String name);
}
