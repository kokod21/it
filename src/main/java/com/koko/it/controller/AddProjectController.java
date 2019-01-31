package com.koko.it.controller;

import com.alibaba.druid.util.StringUtils;
import com.koko.it.common.response.ResponseMessage;
import com.koko.it.entity.Classify;
import com.koko.it.entity.Project;
import com.koko.it.service.ClassifyService;
import com.koko.it.service.ProjectService;
import com.koko.it.utils.LogUtil;
import com.koko.it.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddProjectController {

    @Autowired
    ClassifyService classifyService;
    @Autowired
    ProjectService projectService;

    @RequestMapping("/add_project")
    public String add_project(HttpServletRequest request, String id){
        if(!StringUtils.isEmpty(id)){
            Project gp = projectService.findById(Long.parseLong(id)).get();
            if(gp != null){
                request.setAttribute("gp", gp);
            }
        }
        List<Classify> classifies = classifyService.findAll();
        request.setAttribute("classifies", classifies);
        return "add_project";
    }

    @RequestMapping(value = "/saveGithub", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage saveGithub(Long id, Long classify_id, String url, String title, String author, String remark){
        System.out.println("saveGithub----------------------");
        try {
            Project project;
            if(id == null){
                project = new Project();
                project.setClassifyId(classify_id);
                project.setUrl(url);
                project.setTitle(title);
                project.setAuthor(author);
                project.setRemark(remark);
                project.setCreateTime(TimeUtils.todayToString(TimeUtils.TODAY_TIME));
            } else {
                project = projectService.findById(id).get();
                project.setClassifyId(classify_id);
                project.setUrl(url);
                project.setTitle(title);
                project.setAuthor(author);
                project.setRemark(remark);
            }
            Project gp = projectService.save(project);
            if (gp != null) {
                return ResponseMessage.ok(gp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.saveErrorMsg("saveGithub", e);
        }
        return ResponseMessage.fail("保存数据错误");
    }
}
