package com.mySSM.user.service.impl;

import com.mySSM.user.bean.User;
import com.mySSM.user.dao.UserMapper;
import com.mySSM.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    @Transactional
    public boolean register(User user) {
        int count = this.countByUserName(user.getUserName());
        if (count > 0){
            return false;
        }
        user.setId(UUID.randomUUID().toString());
        return userMapper.insertSelective(user) >0 ? true:false;
    }

    @Override
    public List<User> findList(User user) {
        return userMapper.findAll(user);
    }

    @Override
    public int countByUserName(String userName) {
        return userMapper.countByUserName(userName);
    }


}
