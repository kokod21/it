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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/system/role")
public class SystemRoleController {

    private LogUtil logUtil = new LogUtil(SystemRoleController.class);

    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/system_role")
    public String mainRole(){
        return "system/role/system_role";
    }

    @RequestMapping("/add_role")
    public String addRole(HttpServletRequest request, String id){
        if (StringUtils.isEmpty(id)) {
            request.setAttribute("title", "新增角色");
        } else {
            request.setAttribute("title", "编辑角色数据");
            try {
                Role role = roleService.findById(Long.parseLong(id)).get();
                request.setAttribute("role", role);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "system/role/add_role";
    }

    @RequestMapping(value = "/get_role_list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getRoleList(int page, int limit) {
        try {
            List<Role> roleList =roleService.findAll();
            return ResponseMessage.ok(roleList);
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("getRoleList", e);
        }
        return ResponseMessage.fail("获取数据出错");
    }


    @RequestMapping(value = "/save_role", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveRole(Role role){
        try {
            String username =  SecurityUtils.getSubject().getPrincipal().toString();
            User loginUser = userService.findByUserName(username);
            if (loginUser != null) {
                role.setCreateUserId(loginUser.getId());
            }
            roleService.save(role);
            return ResponseMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("saveRole", e);
        }
        return ResponseMessage.fail("保存角色数据失败");
    }


    @RequestMapping(value = "/get_permission_list_by_tree", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage getPermissionListByTree() {
        try {
            List<Permission> roleList =permissionService.findAll();
            List<PermissionTreeVo> treeVoList = new ArrayList<>();
            for (Permission permission : roleList) {
                if (permission.getPid() == 0) {
                    //一级分类
                    PermissionTreeVo treeVo = new PermissionTreeVo();
                    treeVo.id = permission.getId();
                    treeVo.label = permission.getName();
                    for (Permission innerPermission : roleList) {
                        if (innerPermission.getPid().equals(permission.getId())) {
                            PermissionTreeVo.Children children = new PermissionTreeVo.Children();
                            children.id = innerPermission.getId();
                            children.label = innerPermission.getName();
                            treeVo.children.add(children);
                        }
                    }
                    treeVoList.add(treeVo);
                }
            }
            return ResponseMessage.ok(treeVoList);
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.error("getRoleList", e);
        }
        return ResponseMessage.fail("获取数据出错");
    }

}
