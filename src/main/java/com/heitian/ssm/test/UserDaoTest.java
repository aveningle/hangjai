package com.heitian.ssm.test;

import com.heitian.ssm.dao.TokenDao;
import com.heitian.ssm.dao.UserDao;
import com.heitian.ssm.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-*.xml"})
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TokenDao tokenDao;

    //测试用户登录
    @Test
    public void userlogin() throws Exception {
        User user = new User();
        user.setUserQqId("22");
        User user1=userDao.selectUserByQqId(user);
        System.out.println(user1);

    }



    //测试更新用户
    @Test
    public void updatauser() throws Exception {
        User user = userDao.selectUserById((long) 9);
        System.out.println(user.getUserName());
        System.out.println(user.getUserId());
        //user.setId((long)9);
        user.setUserName("1212");
        user.setUserAddress("dd");
        int result = userDao.updatauser(user);
        System.out.println(result);
    }

    //测试用户注册
    @Test
    public void insertuser() throws Exception {
        User user = new User();
        user.setUserName("ddd");


        userDao.insertuser(user);
        System.out.println();
    }


    @Test
    public void selectUserById() throws Exception {
        User u = userDao.selectUserById((long) 1);
        //System.out.println(u.getUserPwd());
        System.out.println(u.getToken().getToken());

    }

    @Test
    public void selectUserByPhoneOrEmail() throws Exception {
    }

    @Test
    public void selectUserByPhone() {
        User user = userDao.selectUserByPhone("12");
        System.out.println(user.getUserId());


    }

    @Test
    public void selectAllUser() throws Exception {
        List<User> getuser = new ArrayList<User>();
        getuser = userDao.selectAllUser();
        for (User user2 : getuser
                ) {
            System.out.print(user2.getUserId() + "名字为");
            System.out.println(user2.getUserName());


        }
    }

}