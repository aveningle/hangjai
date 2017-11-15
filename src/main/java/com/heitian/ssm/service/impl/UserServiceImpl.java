package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.UserDao;
import com.heitian.ssm.model.User;
import com.heitian.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public User getUserById(Long userId) {
        return userDao.selectUserById(userId);
    }

    public User selectUserByToken(String token) {
        return userDao.selectUserByToken(token);
    }

    @Override
    public User selectUserByQqId(User user) {
        return userDao.selectUserByQqId(user);
    }

    public int insertuser(User user) {
        return userDao.insertuser(user);
    }

    public int updatauser(User user) {
        return userDao.updatauser(user);
    }

    @Override
    public int upDataUserHead(User user) {
        return userDao.upDataUserHead(user);
    }

    @Override
    public int upDataUserPassWord(User user) {
        return userDao.upDataUserPassWord(user);
    }

    public int userLogin(User user1) {
        return userDao.insertuser(user1);
    }

    public User userlogin(User user) {
        return userDao.userlogin(user);
    }

    public User getUserByPhoneOrEmail(String emailOrPhone, Short state) {
        return userDao.selectUserByPhoneOrEmail(emailOrPhone, state);
    }

    public User selectUserByPhone(String phone) {
        return userDao.selectUserByPhone(phone);
    }

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }
}
