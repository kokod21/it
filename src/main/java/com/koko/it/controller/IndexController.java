package com.koko.it.controller;

import com.koko.it.common.constants.Constants;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Permission;
import com.koko.it.entity.User;
import com.koko.it.service.PermissionService;
import com.koko.it.service.UserService;
import com.koko.it.utils.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    LogUtil logUtil = new LogUtil(IndexController.class);

    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/index")
    public String index(HttpServletRequest httpServletRequest){
        try {
            boolean flag = SecurityUtils.getSubject().isPermitted("usermanage");
            System.out.println("-------------------------------------------是否有权限="+flag);

            String username =  SecurityUtils.getSubject().getPrincipal().toString();
            User loginUser = new User();
            loginUser.setUserName("超级无敌管理员");
            if(Constants.DEFAULT_NAME.equals(username)){
                List<Permission> lists = permissionService.findByNameNot("");
                httpServletRequest.setAttribute("lists", lists);
            } else {
                loginUser = userService.findByUserName(username);
                List<Map<String, Object>> lists = permissionService.getPermissionByUserId(loginUser.getId());
                httpServletRequest.setAttribute("lists", lists);
            }
            httpServletRequest.setAttribute("user", loginUser);
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("index页面", e);
        }

        return "index";
    }

    @RequestMapping(value = "/userLogout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage userLogout(){
        System.out.println("userLogout--------------------");
        SecurityUtils.getSubject().logout();
        return ResponseMessage.ok();
    }

}
