package com.mySSM.user.service;

import com.mySSM.user.bean.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    List<User> findList(User user);

    int countByUserName(String userName);
}
