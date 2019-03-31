package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.*;
import com.koko.it.service.PermissionService;
import com.koko.it.service.RolePermissionService;
import com.koko.it.service.RoleService;
import com.koko.it.service.UserService;
import com.koko.it.utils.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    RolePermissionService rolePermissionService;

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
                if (role != null) {
                    List<RolePermission> rolePermissionList = rolePermissionService.findByRoleId(role.getId());
                    if (rolePermissionList != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < rolePermissionList.size(); i++) {
                            if (i == rolePermissionList.size()-1) {
                                sb.append(rolePermissionList.get(i).getPermissionId());
                            } else {
                                sb.append(rolePermissionList.get(i).getPermissionId()).append("-");
                            }
                        }
                        request.setAttribute("ids", sb.toString());
                    }
                }
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
    public ResponseMessage saveRole(Role role, @RequestParam(value = "permissionIds[]") String[] permissionIds){
        try {
            roleService.saveRole(role, permissionIds);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logUtil.error("saveRole", e);
            e.printStackTrace();
        }
        return ResponseMessage.fail("保存角色数据失败");
    }

    @RequestMapping(value = "/delete_role", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteRole(Long id){
        try {
            roleService.deleteRole(id);
            return ResponseMessage.ok();
        } catch (Exception e) {
            logUtil.error("saveRole", e);
            e.printStackTrace();
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
                    treeVo.label = permission.getPermissionName();
                    for (Permission innerPermission : roleList) {
                        if (innerPermission.getPid().equals(permission.getId())) {
                            PermissionTreeVo.Children children = new PermissionTreeVo.Children();
                            children.id = innerPermission.getId();
                            children.label = innerPermission.getPermissionName();
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
