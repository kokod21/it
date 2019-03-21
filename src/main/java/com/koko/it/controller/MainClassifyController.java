package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Classify;
import com.koko.it.service.ClassifyService;
import com.koko.it.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/main/classify")
public class MainClassifyController {
    LogUtil logUtil = new LogUtil(MainClassifyController.class);

    @Autowired
    ClassifyService classifyService;

    @RequestMapping("/main_classify")
    public String mainClassify(){
        return "main/classify/main_classify";
    }

    @RequestMapping("/add_classify")
    public String addClassify(HttpServletRequest request, String id){
        if(StringUtils.isEmpty(id)){
            request.setAttribute("title", "新增分类数据");
            List<Classify> lists = classifyService.findByParentId(0l);
            request.setAttribute("lists", lists);
        } else {
            request.setAttribute("title", "编辑分类数据");
            try {
                Classify classify = classifyService.findById(Long.parseLong(id)).get();
                request.setAttribute("classify", classify);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return "main/classify/add_classify";
    }

    @RequestMapping(value = "/get_classify_list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getClassifyList() {
        List<Classify> classifies = classifyService.findAll();
        return ResponseMessage.ok(classifies);
    }

    @RequestMapping(value = "/save_classify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveClassify(Classify classify){
        System.out.println("saveProject----------------------");
        try {
            if(classify.getId() == null){
                Classify gp = classifyService.save(classify);
                if (gp != null) {
                    return ResponseMessage.ok(gp);
                }
            } else {
                Classify oldClassify = classifyService.findById(classify.getId()).get();
                oldClassify.setClassifyName(classify.getClassifyName());
                Classify gp = classifyService.save(oldClassify);
                if (gp != null) {
                    return ResponseMessage.ok(gp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logUtil.saveErrorMsg("saveProject", e);
        }
        return ResponseMessage.fail("保存数据错误");
    }

    @RequestMapping(value = "/delete_classify", method = RequestMethod.POST)
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
