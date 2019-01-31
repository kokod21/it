package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainRoleController {

    @RequestMapping("/main_role")
    public String main_role(){
        return "system/main_role";
    }

    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getRoleList(int page, int limit) {
        return ResponseMessage.fail("获取数据出错");
    }

}
