package com.github.lovesick.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dujiang on 2016/4/5.
 */
public class AuthenticatorTest {
    private void login(String iniFile){
        Factory<org.apache.shiro.mgt.SecurityManager> factory=
                new IniSecurityManagerFactory(iniFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        //得到Subject
        //创建用户名,密码身份验证Token
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        subject.login(token);
    }

    //测试策略
    @Test
    public void testAllSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection previousPrincipals = subject.getPreviousPrincipals();
        Assert.assertEquals(2,previousPrincipals.asList().size());

    }
}
