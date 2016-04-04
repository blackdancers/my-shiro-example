package com.github.lovesick.shiro.chapter2;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by dujiang on 2016/4/4.
 */
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myrealm1";//返回一个唯一的Realm名字
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;//支持类型,仅支持UsernamePasswordToken

    }

    /**
     *  根据token获取认证信息
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();//得到用户名
        String password = new String((char[]) token.getCredentials());//得到密码
        if (!"lovesick".equals(username)) {
            throw new UnknownAccountException("用户名错误!!!");
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误!!!");
        }
        //验证成功!!!
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}







