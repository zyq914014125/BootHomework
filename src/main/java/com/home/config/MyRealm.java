package com.home.config;

//import com.dao.LoginUserMapper;
//import com.dao.departmenMapper;
//import com.pojo.LoginUser;
//import com.pojo.departmen;

import com.home.module.home3.Service.impl.ResourceServiceImpl;
import com.home.module.home3.Service.impl.UserServiceImpl;
import com.home.module.home3.entity.Resource;
import com.home.module.home3.entity.Role;
import com.home.module.home3.entity.user;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ResourceServiceImpl resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        user user = (user) principals.getPrimaryPrincipal();
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()) {
            roles.stream().forEach(item -> {
                simpleAuthorizationInfo.addRole(item.getRoleName());
                List<Resource> resources =
                        resourceService.getResourcesByRoleId(item.getRoleId());
                if (resources != null && !resources.isEmpty()) {
                    resources.stream().forEach(resource -> {
                        simpleAuthorizationInfo.addStringPermission(resource.getPermission());
                    });
                }
            });
        }

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //1.将token强转为UsernamePasswordToken类型（可以通过这个类型可以拿到身份(用户名)）
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        System.out.println("" + token1.getUsername());
        user loginUser = userService.selectByusername(token1.getUsername());
        //System.out.println(p);
        if (loginUser != null) {
            //2.设置shiro比对器身份
            Object principal = loginUser.getUserName();
            //3.设置比对器里面的密码(把数据库里面的密码作为比对密码)
            Object credentials = loginUser.getPassword();
            //4.自动给令牌类里面的用户名设置加盐方式
            ByteSource salt = ByteSource.Util.bytes(loginUser.getUserName());
            //5.设置realm的名称
            String realmName = this.getName();
            //new SimpleAuthenticationInfo(principal,credentials,salt,realmName)才是真正的认证
            /*
           如何认证：
           将上面的principal和token1里面的用户名来比对
           将上面的credentials和token1里面的密码来比对
           因为shirocofig里面配置加密方式 所以会自动把token1里面的密码以MD5加密，加密1024次
           因为上面传了个salt，表示再把token1里面的密码再以salt加盐
           * */
            return new SimpleAuthenticationInfo(principal, credentials, salt, realmName);
        } else {
            throw new AuthenticationException();
        }
    }
}
