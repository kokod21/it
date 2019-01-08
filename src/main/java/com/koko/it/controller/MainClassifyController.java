package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Blog;
import com.koko.it.entity.Classify;
import com.koko.it.service.BlogService;
import com.koko.it.service.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class MainClassifyController {
    @Autowired
    ClassifyService classifyService;

    @RequestMapping("/main_classify")
    public String main_classify(HttpServletRequest request){
        List<Classify> classifies = classifyService.findAll();
        request.setAttribute("classifies", classifies);
        return "main_classify";
    }

    @RequestMapping(value = "/getClassifyList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getClassifyList() {
        List<Map<String, Object>> classifies = classifyService.findAllClassify();
        return ResponseMessage.ok(classifies);
    }

}
