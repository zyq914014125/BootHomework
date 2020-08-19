package com.home.config;

//import com.dao.LoginUserMapper;
//import com.dao.departmenMapper;
//import com.pojo.LoginUser;
//import com.pojo.departmen;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName MyRealm
 * @Description 自定义的reaml类   实现用户认证和授权
 * @Author ss
 * @Date 2020/7/28 10:56
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {
//    @Autowired
//    private UserMapper userMapper;
//    @Autowired
//    private departmenMapper departmenMappe;
    //用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        //授权
//        //获取当前用户
        return null;
    }
//    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        //1.将token强转为UsernamePasswordToken类型（可以通过这个类型可以拿到身份(用户名)）
//        UsernamePasswordToken token1 = (UsernamePasswordToken)token;
//        System.out.println(token1.getUsername());
//        User loginUser = userMapper.selectByName(token1.getUsername());
//        //System.out.println(p);
//        if(loginUser!=null){
//            //2.设置shiro比对器身份
//            Object principal = loginUser.getUserName();
//            //3.设置比对器里面的密码(把数据库里面的密码作为比对密码)
//            Object credentials = loginUser.getPassword();
//            //4.自动给令牌类里面的用户名设置加盐方式
//            ByteSource salt= ByteSource.Util.bytes(loginUser.getUserName());
//            //5.设置realm的名称
//            String realmName = this.getName();
//            //new SimpleAuthenticationInfo(principal,credentials,salt,realmName)才是真正的认证
//            /*
//           如何认证：
//           将上面的principal和token1里面的用户名来比对
//           将上面的credentials和token1里面的密码来比对
//           因为spring-shiro.xml里面配置加密方式 所以会自动把token1里面的密码以MD5加密，加密1024次
//          因为上面传了个salt，表示再把token1里面的密码再以salt加盐
//           * */
//           return  new SimpleAuthenticationInfo(principal,credentials,salt,realmName);
//        }else{
//            throw  new AuthenticationException();
//        }
//
  return null;
    }

    public static void main(String[] args) {
        //加密测试代码
        //设置加密方式
        String algorithmName="MD5";
        //设置待加密的原密码
        Object source="123";
        //设置加盐方式(一般来说都是以用户名来加盐)
        Object salt= ByteSource.Util.bytes("yjq");
        //加密次数
        int hashIterations=1024;
        SimpleHash newPassword=new SimpleHash(algorithmName, source, salt, hashIterations);
        System.out.println(newPassword.toString());
    }
}
