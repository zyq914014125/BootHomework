package com.home.module.home3.Controller;

import com.home.Serach.Result;
import com.home.module.home1.entity.City;
import com.home.module.home3.Service.impl.UserServiceImpl;
import com.home.module.home3.entity.user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mr.X
 **/
@RestController
@RequestMapping("/use")
public class loginControllerRest {
    @Autowired
    UserServiceImpl userService;
    //新增
    @PostMapping(value = "/put",consumes = "application/json")
    public Result<user> insert( user user){
        return userService.InsertUser(user);
    }

    @PostMapping (value = "/post",consumes = "application/json")
    public Result<Object> login(@RequestBody user user){
        //当前对象
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
            // remembermMe记住密码
            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);
                // 登录成功...
                return new Result<>(Result.ResultState.SUCCESS_RESPONSE,"/user/main/index");
            } catch (IncorrectCredentialsException e) {
                return new Result<>(Result.ResultState.SOMETHING_WRONG,"登录密码错误");
            } catch (ExcessiveAttemptsException e) {
                return new Result<>(Result.ResultState.SOMETHING_WRONG,"登录失败次数过多");
            } catch (LockedAccountException e) {
                return new Result<>(Result.ResultState.SOMETHING_WRONG,"帐号已被锁定");
            } catch (DisabledAccountException e) {
                return new Result<>(Result.ResultState.SOMETHING_WRONG,"帐号已被禁用");
            } catch (ExpiredCredentialsException e) {
                return new Result<>(Result.ResultState.SOMETHING_WRONG,"帐号已过期");
            } catch (UnknownAccountException e) {
                return new Result<>(Result.ResultState.SOMETHING_WRONG, "帐号不存在");
            } catch (Exception e) {
                return new Result<>(Result.ResultState.SOMETHING_WRONG,"something error");
            }
        }
        // 登录成功，重定向到LoginSuccess.action
        return new Result<>(Result.ResultState.SUCCESS_RESPONSE,"/user/main/index");
    }
}
