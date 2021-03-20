package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.ManageDubboService;
import com.ego.mapper.ManagerMapper;
import com.ego.pojo.Manager;
import com.ego.pojo.ManagerExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ManagerDubboServiceImpl implements ManageDubboService {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public Manager selectManagerByUsername(String username) {
        ManagerExample example = new ManagerExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<Manager> userList = managerMapper.selectByExample(example);
        if(userList != null && userList.size() > 0){
            return userList.get(0);
        }
        return null;
    }
}
