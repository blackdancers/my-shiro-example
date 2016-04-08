package com.github.lovesick.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * 指定两个主体在shiro.ini文件中
 * Created by dujiang on 2016/4/4.
 */
public class LoginAndOut {

    @After
    public void tearDown() {//程序退出时解除Subject线程绑定
        ThreadContext.unbindSubject();
    }

    @Test
    public void testJDBCRealm() {
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            subject.login(token);
            System.out.println("jdbc realm!!!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录

        subject.logout();
    }

    @Test
    public void testCustomRealm() {
        //1,获取SecurityManager工厂，此处使用配置文件ini初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        //2,获得securityManager实例，并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3,得到Subject,创建用户名和密码——身份验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lovesick", "123");

        try {
            //4,登录，（即身份验证）
            subject.login(token);
            System.out.println("custom realm!!!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录

        //5，退出
        subject.logout();
    }

    @Test
    public void testHelloLogin() {
        //1,获取SecurityManager工厂，此处使用配置文件ini初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2,获得securityManager实例，并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3,得到Subject,创建用户名和密码——身份验证Token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lovesick", "123");

        try {
            //4,登录，（即身份验证）
            subject.login(token);
            System.out.println("login success!!!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated());//断言用户已经登录

        //5，退出
        subject.logout();


    }
}

















