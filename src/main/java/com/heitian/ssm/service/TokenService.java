package com.heitian.ssm.service;

import com.heitian.ssm.model.Token;

public interface TokenService {

    int insertToken(Token token);

    int deleteToken(long userId);

    Token getTokenByid(long id);


}
