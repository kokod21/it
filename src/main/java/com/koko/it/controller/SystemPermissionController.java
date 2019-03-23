package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Permission;
import com.koko.it.entity.PermissionTreeVo;
import com.koko.it.entity.Role;
import com.koko.it.entity.User;
import com.koko.it.service.PermissionService;
import com.koko.it.service.RoleService;
import com.koko.it.service.UserService;
import com.koko.it.utils.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/system/permission")
public class SystemPermissionController {

    private LogUtil logUtil = new LogUtil(SystemPermissionController.class);

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/system_permission")
    public String mainRole(){
        return "system/permission/system_permission";
    }

    @RequestMapping("/add_permission")
    public String addRole(HttpServletRequest request, String id){
        if (StringUtils.isEmpty(id)) {
            request.setAttribute("title", "新增角色");
        } else {
            request.setAttribute("title", "编辑角色数据");
            try {
                Permission permission = permissionService.findById(Long.parseLong(id)).get();
                request.setAttribute("permission", permission);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "system/permission/add_permission";
    }

    @RequestMapping(value = "/get_permission_list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getPermissionList(int page, int limit, Long pid) {
        try {
            List<Permission> permissionList;
            if (pid == null) {
//                permissionList = permissionService.findAll();
                permissionList = permissionService.findAll(PageRequest.of(page-1, limit, Sort.by(Sort.Order.desc("createTime")))).getContent();
            } else {
                permissionList =
                        permissionService.findByPid(pid, PageRequest.of(page-1, limit, Sort.by(Sort.Order.desc("createTime"))));
            }
            return ResponseMessage.ok(permissionList);
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("getPermissionList", e);
        }
        return ResponseMessage.fail("获取数据出错");
    }


//    @RequestMapping(value = "/save_permission", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseMessage savePermission(Permission permission){
//        try {
//            String username =  SecurityUtils.getSubject().getPrincipal().toString();
//            User loginUser = userService.findByUserName(username);
//            if (loginUser != null) {
//                permission.setCreateUserId(loginUser.getId());
//            }
//            permissionService.save(permission);
//            return ResponseMessage.ok();
//        } catch (Exception e) {
//            e.printStackTrace();
//            logUtil.error("savePermission", e);
//        }
//        return ResponseMessage.fail("保存权限数据失败");
//    }

}
