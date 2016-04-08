package com.github.lovesick.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by dujiang on 2016/4/4.
 */
public class MyRealm2 implements Realm{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();//得到用户名
        String password = new String((char[]) token.getCredentials());//得到密码
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException("用户名错误!!!");
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误!!!");
        }
        //验证成功!!!
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
