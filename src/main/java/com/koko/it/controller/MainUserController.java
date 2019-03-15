package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainUserController {

    @RequestMapping("/main_user")
    public String main_user(){
        return "system/main_user";
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getUserList(int page, int limit) {
        return ResponseMessage.fail("获取数据出错");
    }

}
