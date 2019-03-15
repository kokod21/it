package com.koko.it.controller;

import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Classify;
import com.koko.it.service.ClassifyService;
import com.koko.it.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainClassifyController {
    LogUtil logUtil = new LogUtil(MainClassifyController.class);

    @Autowired
    ClassifyService classifyService;

    @RequestMapping("/main_classify")
    public String main_classify(){
        return "main_classify";
    }

    @RequestMapping(value = "/getClassifyList", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getClassifyList() {
        List<Classify> classifies = classifyService.findAll();
        return ResponseMessage.ok(classifies);
    }

    @RequestMapping(value = "/deleteClassify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage deleteClassify(Long id) {
        try {
            classifyService.deleteClassifyById(id, id);
            return ResponseMessage.ok();
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.saveErrorMsg("getProjectList", e);
        }
        return ResponseMessage.fail("删除失败，请重试");
    }

}
