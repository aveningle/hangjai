package com.heitian.ssm.dao;

import com.heitian.ssm.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    User selectUserById(@Param("userId") Long userId);

    User selectUserByPhone(@Param(("phone"))String phone);

    User selectUserByToken(@Param("token") String token);//根据Token查出用户

    User selectUserByQqId(User user);//用户通过qq登录

    User selectUserByPhoneOrEmail(@Param("emailOrPhone") String emailOrPhone, @Param("state") Short state);

    List<User> selectAllUser();

    int insertuser(User user);

    int updatauser(User user);

    int upDataUserHead(User user);

    int upDataUserPassWord(User user);


    User userlogin(User user);//用户登录


}
