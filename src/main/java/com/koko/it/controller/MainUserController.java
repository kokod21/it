package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Blog;
import com.koko.it.entity.Project;
import com.koko.it.service.BlogService;
import com.koko.it.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
