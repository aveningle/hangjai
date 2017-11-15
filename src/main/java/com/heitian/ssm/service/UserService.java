package com.heitian.ssm.service;

import com.heitian.ssm.model.User;

import java.util.List;


public interface UserService {

    List<User> getAllUser();

    User getUserByPhoneOrEmail(String emailOrPhone, Short state);

    User selectUserByPhone(String phone);

    User getUserById(Long userId);

    User selectUserByToken(String token);

    User selectUserByQqId(User user);//用户通过qq登录

    int insertuser(User user);

    int updatauser(User user);

    int upDataUserHead(User user);

    int upDataUserPassWord(User user);



    User userlogin(User user);

}
