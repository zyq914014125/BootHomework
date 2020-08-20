package com.home.module.home3.Service.impl;
import com.home.Serach.Result;
import com.home.module.home3.entity.user;
import com.home.module.home3.mapper.userMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mr.X
 **/
@Service
public class UserServiceImpl {
    @Autowired
    userMapper userMapper;
    //新增
    @Transactional
  public   Result<user> InsertUser(user user){
        if(userMapper.selectByPrimaryKey(user.getName())!=null){
            return new Result<user>(Result.ResultState.SOMETHING_WRONG,"用户名已存在",user);
        }
        //加密测试代码
        //设置加密方式
        String algorithmName="MD5";
        //设置待加密的原密码
        Object source=user.getPassword();
        //设置加盐方式(一般来说都是以用户名来加盐)
        Object salt= ByteSource.Util.bytes(user.getName());
        //加密次数
        int hashIterations=1024;
        SimpleHash newPassword=new SimpleHash(algorithmName, source, salt, hashIterations);
        user.setPassword(newPassword.toString());
        userMapper.insert(user);
        return new Result<user>(Result.ResultState.SUCCESS_RESPONSE,"注册成功",user);
    }
}
