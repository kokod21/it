package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/role")
public class SystemRoleController {

    @RequestMapping("/system_role")
    public String main_role(){
        return "system/role/system_role";
    }

    @RequestMapping(value = "/get_role_list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getRoleList(int page, int limit) {
        return ResponseMessage.fail("获取数据出错");
    }

}
