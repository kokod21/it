package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Blog;
import com.koko.it.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/get_blogs", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage get_blogs() {
        List<Blog> blogList = blogService.findAll();
        return ResponseMessage.ok(blogList);
    }
}
