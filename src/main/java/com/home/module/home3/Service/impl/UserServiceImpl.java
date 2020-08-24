package com.home.module.home3.Service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.home.Serach.Result;
import com.home.Serach.Serachvo;
import com.home.config.ResourceConfig;
import com.home.module.home3.entity.user;
import com.home.module.home3.mapper.userMapper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.net.dns.ResolverConfiguration;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @author Mr.X
 **/
@Service
public class UserServiceImpl {
    @Autowired
    userMapper userMapper;
    @Autowired
    ResourceConfig resourceConfig;

    //新增
    @Transactional
    public Result<user> InsertUser(user user) {
        if (userMapper.selectByPrimaryKey(user.getUserName()) != null) {
            return new Result<user>(Result.ResultState.SOMETHING_WRONG, "用户名已存在", user);
        }
        //加密测试代码
        //设置加密方式
        String algorithmName = "MD5";
        //设置待加密的原密码
        Object source = user.getPassword();
        //设置加盐方式(一般来说都是以用户名来加盐)
        Object salt = ByteSource.Util.bytes(user.getUserName());
        //加密次数
        int hashIterations = 1024;
        SimpleHash newPassword = new SimpleHash(algorithmName, source, salt, hashIterations);
        user.setPassword(newPassword.toString());
        LocalDateTime date = LocalDateTime.now();
        user.setCreateDate(date);
        userMapper.insert(user);
        return new Result<user>(Result.ResultState.SUCCESS_RESPONSE, "注册成功", user);
    }

    //分页
    public PageInfo<user> getusersBySerchvo(Serachvo serachvo) {
        PageHelper.startPage(serachvo.getCurrentPage(), serachvo.getPageSize());
        PageInfo<user> pageInfo = new PageInfo<user>(userMapper.getusersBySerchvo(serachvo));
        return Optional.ofNullable(pageInfo).orElse(new PageInfo<>());
    }

    //删除
    public Result<Object> delete(int userId) {
        userMapper.delete(userId);
        return new Result<>(Result.ResultState.SUCCESS_RESPONSE, "删除成功");
    }

    //更新
    public Result<user> update(user user) {
        userMapper.update(user);
        return new Result<user>(Result.ResultState.SUCCESS_RESPONSE, "更新成功", user);
    }

    //Id获取
    public user getUserById(int userId) {
        return userMapper.getuserById(userId);
    }
    //图片设置
    public Result<String> setImg(MultipartFile multipartFile) {
        //判空
        if (multipartFile.isEmpty()) {
            return new Result<>(Result.ResultState.SOMETHING_WRONG, "please select img");
        }
        String relativePath = "";
        String destFilePath = "";
        //获取本机mac
        try {
            String osname = System.getProperty("os.name");
            if (osname.toLowerCase().startsWith("win"))
                destFilePath = resourceConfig.getLocationPathForWindows() + multipartFile.getOriginalFilename();
            else
                destFilePath = resourceConfig.getLocationPathForLinux() + multipartFile.getOriginalFilename();
            relativePath = resourceConfig.getRelativePath() + multipartFile.getOriginalFilename();
            File destFile = new File(destFilePath);
            multipartFile.transferTo(destFile);
        } catch (IOException e) {
          return new Result<String> (Result.ResultState.SOMETHING_WRONG,"upload Fail");
        }
        return new Result<String>(
                Result.ResultState.SUCCESS_RESPONSE, "Upload success.", relativePath);
    }

    //用户名更改
    @Transactional
    public Result<user> updateProfile(user user){
        user user1=userMapper.selectByPrimaryKey(user.getUserName());
       //检测用户名是否被其他人使用
        if(user1!=null&&user1.getUserId()!=user.getUserId()){
            return new Result<user>(Result.ResultState.SOMETHING_WRONG, "User name have been used.");
        }
        userMapper.update(user);
        return new Result<user>(Result.ResultState.SUCCESS_RESPONSE, "Update success.",user);
    }
}
