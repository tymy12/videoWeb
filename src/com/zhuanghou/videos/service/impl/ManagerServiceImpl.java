package com.zhuanghou.videos.service.impl;

import com.zhuanghou.videos.repository.domain.Manager;
import com.zhuanghou.videos.repository.repos.ManagerRepos;
import com.zhuanghou.videos.repository.repos.impl.MangerReposImpl;
import com.zhuanghou.videos.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by duhui on 2017/11/24.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepos mangerRepos ;

    @Override
    public Manager findManagerByUsername(String username) {
        return mangerRepos.queryBy(username);
    }
}
