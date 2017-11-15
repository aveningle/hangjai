package com.heitian.ssm.service.impl;

import com.heitian.ssm.dao.TokenDao;
import com.heitian.ssm.model.Token;
import com.heitian.ssm.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional(rollbackFor = Exception.class)
public class TokenServiceImpl implements TokenService {

    @Resource
    private TokenDao tokenDao;


    public int insertToken(Token token) {
        return tokenDao.insertToken(token);
    }

    public int deleteToken(long userId) {
        return tokenDao.deleteToken(userId);
    }

    public Token getTokenByid(long id) {
        return tokenDao.getTokenByid(id);
    }
}
