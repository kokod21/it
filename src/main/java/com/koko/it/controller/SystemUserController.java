package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Role;
import com.koko.it.entity.User;
import com.koko.it.entity.UserRole;
import com.koko.it.service.RoleService;
import com.koko.it.service.UserRoleService;
import com.koko.it.service.UserService;
import com.koko.it.utils.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/user")
public class SystemUserController {

    private LogUtil logUtil = new LogUtil(SystemUserController.class);

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;

    @RequestMapping("/system_user")
    public String mainUser(){
        return "system/user/system_user";
    }

    @RequestMapping("/add_user")
    public String addUser(HttpServletRequest request, String id){
        if (StringUtils.isEmpty(id)) {
            request.setAttribute("title", "新增用户");
        } else {
            request.setAttribute("title", "编辑用户");
            try {
                Map<String, Object> user = userService.findUserAndRole(Long.parseLong(id));
                request.setAttribute("user", user);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        List<Role> roleList = roleService.findAll();
        request.setAttribute("roleList", roleList);
        return "system/user/add_user";
    }

    @RequestMapping(value = "/get_user_list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getUserList(int page, int limit) {
        try {
            List<Map<String, Object>> userList = userService.findAllUser(
                    PageRequest.of(page-1, limit, Sort.by(Sort.Order.desc("create_time"))));
            return ResponseMessage.ok(userList);
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("getUserList", e);
        }
        return ResponseMessage.fail("获取数据出错");
    }

    @Transactional
    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveUser(User user, Long roleId){
        try {
            if (user.getId() == null) {
                //新增用户
                // TODO shiro配置createUserId和updateUserID
                //这里暂时手动添加createUserId和updateUserID
                String username =  SecurityUtils.getSubject().getPrincipal().toString();
                User loginUser = userService.findByUserName(username);
                if (loginUser != null) {
                    user.setCreateUserId(loginUser.getId());
                    user.setUpdateUserId(loginUser.getId());
                }
                userService.saveUser(user, roleId);
            } else {
                // 修改用户
                String username =  SecurityUtils.getSubject().getPrincipal().toString();
                User loginUser = userService.findByUserName(username);
                User oldUser = userService.findById(user.getId()).get();
                oldUser.setUserName(user.getUserName());
                oldUser.setEmail(user.getEmail());
                oldUser.setMobile(user.getMobile());
                oldUser.setRemark(user.getRemark());
                if (loginUser != null) {
                    user.setUpdateUserId(loginUser.getId());
                }
                userService.saveUser(oldUser, roleId);
            }
            return ResponseMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("saveUser", e);
        }
        return ResponseMessage.fail("保存用户失败，请重试！！！");
    }


    @RequestMapping(value = "/set_user_delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage setUserDelete(Long id, int is_del){
        try {
            User user = userService.findById(id).get();
            user.setIsDel(is_del);
            userService.save(user);
            return ResponseMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("deleteUser", e);
        }
        return ResponseMessage.fail("删除用户失败，请重试！！！");
    }

    @RequestMapping(value = "/delete_user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteUser(Long id){
        try {
            User user = userService.findById(id).get();
            userService.delete(user);
            return ResponseMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("deleteUser", e);
        }
        return ResponseMessage.fail("删除用户失败，请重试！！！");
    }

}
