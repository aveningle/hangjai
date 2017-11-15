package com.heitian.ssm.test;

import com.heitian.ssm.dao.TokenDao;
import com.heitian.ssm.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-*.xml"})
public class TokenDaoTest {

    @Resource
    private TokenDao tokenDao;
    @Resource
    private UserDao userDao;


    @Test

    public void test() throws Exception {

    }



}