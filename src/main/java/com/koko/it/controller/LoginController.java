package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.User;
import com.koko.it.service.UserService;
import com.koko.it.utils.LogUtil;
import com.koko.it.utils.token.Md5Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    private LogUtil logUtil = new LogUtil(LoginController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String indexLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage userLogin(User user, boolean rememberMe){
        System.out.println("userLogin=============================================");
        System.out.println(user);
        System.out.println(rememberMe);
        if (user == null) {
            return ResponseMessage.fail("登录用户参数错误");
        }
        try {
            // shiro登录验证开始
            UsernamePasswordToken token  = new UsernamePasswordToken(user.getUserName(), DigestUtils.md5Hex(user.getPassword()));
            // TODO 1、 封装用户名、密码、是否记住我到token令牌对象
            // token.setRememberMe(true);
            // 2、 Subject调用login
            Subject subject = SecurityUtils.getSubject();
            // 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            // 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            // 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            System.out.println("用户登录，用户验证开始！user=" + user.getUserName());
            subject.login(token);
            return ResponseMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.saveErrorMsg("userLogin", e);
        }
        return ResponseMessage.fail("用户名或者密码错误！！！");
    }

}
