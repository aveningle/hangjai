package com.heitian.ssm.dao;

import com.heitian.ssm.model.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TokenDao {

   int insertToken(Token token);//用户登录，生成Token
   int deleteToken(@Param("userId")long userId);//用户登出，删除Token
   Token getTokenByid(@Param("tokenId")long id);

}
